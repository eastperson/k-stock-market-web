package com.k_stock_market.web.scheduler;


import com.k_stock_market.web.service.FileReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;

@RequiredArgsConstructor
public class FileReadScheduler {

    private final FileReadService fileReadService;

    @Scheduled(cron = "0 0 * * * *", zone = "Asia/Seoul")
    private void fileRead(){
        fileReadService.fileRead();
    }

}
