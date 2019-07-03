package com.example.springboot.sample002;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import com.example.springboot.sample002.dto.TrainInformationDto;
import com.example.springboot.sample002.service.TokyoMetroService;
import com.linecorp.bot.client.LineMessagingService;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

@SpringBootApplication
@LineMessageHandler
public class LineBotSample002Application {
	
	private static Map<String, String> railwayMap = new HashMap<String, String>() {
	    {
	        put("東西線", "odpt.Railway:TokyoMetro.Tozai");
	        put("日比谷線", "odpt.Railway:TokyoMetro.Hibiya");
	        put("千代田線", "odpt.Railway:TokyoMetro.Chiyoda");
	        put("副都心線", "odpt.Railway:TokyoMetro.Fukutoshin");
	        put("銀座線", "odpt.Railway:TokyoMetro.Ginza");
	        put("半蔵門線", "odpt.Railway:TokyoMetro.Hanzomon");
	        put("丸ノ内線", "odpt.Railway:TokyoMetro.Marunouchi");
	        put("南北線", "odpt.Railway:TokyoMetro.Namboku");
	        put("有楽町線", "odpt.Railway:TokyoMetro.Yurakucho");
	    }
	};
	
	@Autowired
    private LineMessagingService lineMessagingService;
	
	public static void main(String[] args) {
		SpringApplication.run(LineBotSample002Application.class, args);
		System.out.println("hello");
	}
	
    @EventMapping
    public void handleTextMessageEvent(MessageEvent<TextMessageContent> event) throws Exception {
    	
    	String railway = railwayMap.get(event.getMessage().getText());
    	System.out.println("railway: " + railway);
    	
//    	TrainInformationDto dto = restTemplate.getForObject(API_ENDPOINT, TrainInformationDto.class, railway);
    	TokyoMetroService service = new TokyoMetroService();
    	TrainInformationDto[] dto = service.getTrainInfomation(railway);
    	String trainInformationText = dto.length > 0 ? dto[0].getTrainInformationText() : "そんな路線は東京メトロにはないよ！";
    	
//        final BotApiResponse apiResponse = lineMessagingService
//            .replyMessage(new ReplyMessage(event.getReplyToken(),
//                                           Collections.singletonList(new TextMessage(event.getSource().getUserId()))))
//            .execute().body();
    	final BotApiResponse apiResponse = lineMessagingService
                .replyMessage(new ReplyMessage(event.getReplyToken(),
                                               Collections.singletonList(new TextMessage(trainInformationText))))
                .execute().body();
        System.out.println("Sent messages: " + apiResponse);
    }

    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        System.out.println("event: " + event);
    }

}
