package ru.itis.consumer.service;


import ru.itis.consumer.dto.UserDto;

public interface ReportService {
    public byte[] createProjectPassportReportPDF(UserDto userDto);
}
