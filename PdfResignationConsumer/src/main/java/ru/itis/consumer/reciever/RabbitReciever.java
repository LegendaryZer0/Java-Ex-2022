package ru.itis.consumer.reciever;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.itis.consumer.model.dto.ReportDto;
import ru.itis.consumer.model.dto.UserDto;
import ru.itis.consumer.service.ReportService;

@Slf4j
@Component
@RequiredArgsConstructor
public class RabbitReciever {

    private final ReportService reportService;
    @RabbitListener(queues = "resignation-queue")
    public ReportDto listen(UserDto userDto) {
        log.info("Message read from myQueue : " + userDto.toString());
        return ReportDto.builder().bytes(reportService.createProjectPassportReportPDF(userDto)).build();
    }
}
