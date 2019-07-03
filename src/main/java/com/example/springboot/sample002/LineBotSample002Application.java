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
	
	static private final String API_ENDPOINT = "https://api.tokyometroapp.jp/api/v2/datapoints?rdf:type=odpt:TrainInformation&acl:consumerKey=b26ce611b4195e1ca43197c4ca9bc19082afe5b93a61bf4cb60036346f2cd797&odpt:railway={railway}";
	
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
	
	@Autowired
	@Qualifier("trainInfomationSearchRestTemplate")
	private RestTemplate restTemplate;

	public static void main(String[] args) {
		SpringApplication.run(LineBotSample002Application.class, args);
		System.out.println("hello");
	}
	
    @EventMapping
    public void handleTextMessageEvent(MessageEvent<TextMessageContent> event) throws Exception {
    	
    	String railway = railwayMap.get(event.getMessage().getText());
    	System.out.println("railway: " + railway);
    	
    	System.out.println("size: " + restTemplate.getMessageConverters().size());
    	TrainInformationDto dto = restTemplate.getForObject(API_ENDPOINT, TrainInformationDto.class, railway);
    	
        final BotApiResponse apiResponse = lineMessagingService
            .replyMessage(new ReplyMessage(event.getReplyToken(),
                                           Collections.singletonList(new TextMessage(event.getSource().getUserId()))))
            .execute().body();
        System.out.println("Sent messages: " + apiResponse);
    }

    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        System.out.println("event: " + event);
    }

}
