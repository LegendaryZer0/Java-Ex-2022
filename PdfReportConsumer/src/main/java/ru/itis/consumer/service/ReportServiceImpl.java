package ru.itis.consumer.service;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;
import ru.itis.consumer.dto.UserDto;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

  private final PdfGeneratorService pdfGeneratorService;

  /**
   * Создаёт отчёт pdf из объекта JasperPrint и конвертирует в массив байт
   *
   * @return массив байт pdf отчёта
   */
  public byte[] createProjectPassportReportPDF(UserDto userDto) {
    var frontPage = this.getFrontPage(userDto);
    return pdfGeneratorService.createReportFromJasperPrint(frontPage, "projectPassportFirstPage");
  }

  /**
   * Создаёт объект основных параметров проекта из его объекта-паспорта в виде набора пар
   * ключ-значение
   *
   * @return наборы пар ключ-значение основных параметров проекта
   */
  private Map<String, Object> createProjectPassportGeneralInfoCommonParams(UserDto userDto) {
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("userEmail", userDto.getEmail());
    parameters.put("userName", userDto.getName());
    parameters.put("userSurname", userDto.getSurname());
    parameters.put("userRole",  userDto.getRole());
    return parameters;
  }

  /**
   * Создает титульную страницу отчёта
   *
   * @return Возвращает титульную страницу отчёта
   */
  private JasperPrint getFrontPage(UserDto userDto) {
    Map<String, Object> commonParams = createProjectPassportGeneralInfoCommonParams(userDto);
    return pdfGeneratorService.createJasperPrint(
        Collections.emptyList(), commonParams, "report/template/report_page.jrxml");
  }
}
