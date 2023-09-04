package GDG.backend.global.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "naver-cloud-sms")
public record SmsConfig (
        String accessKey,
        String secretKey,
        String serviceId,
        String senderPhone
) {
}
