package org.jsmpp.examples;
//
//import com.github.mfathi91.time.PersianDate;
//import helpers.Utl;
//import lombok.extern.slf4j.Slf4j;

import org.jsmpp.InvalidResponseException;
import org.jsmpp.PDUException;
import org.jsmpp.bean.*;
import org.jsmpp.extra.NegativeResponseException;
import org.jsmpp.extra.ResponseTimeoutException;
import org.jsmpp.session.BindParameter;
import org.jsmpp.session.SMPPSession;
import org.jsmpp.util.AbsoluteTimeFormatter;
import org.jsmpp.util.TimeFormatter;

import java.io.IOException;
import java.util.Date;

//@Slf4j
public class JsmppSample {
    private static final TimeFormatter TIME_FORMATTER = new AbsoluteTimeFormatter();

    public static void main(String[] args) {

        String userName = "YarangandmFara";
        String password = "Yar@GF51";
        String providedSystemType = "SMPP";
        String ipAddress = "10.200.114.15";
        String destinationPhoneNumber = "989209206311";
        String serverPortString = "2775";
        int serverPortNumber = 2775;
        String sourcePhoneNumber = "986369";
        ESMClass esmClass = new ESMClass();
        DataCoding dataCoding = new GeneralDataCoding(Alphabet.ALPHA_8_BIT);
        String scheduleDeliveryTime = TIME_FORMATTER.format(new Date());
        RegisteredDelivery registeredDelivery = new RegisteredDelivery(SMSCDeliveryReceipt.DEFAULT);
//        String formattedPersianDate =
//                Utl.getFormattedPersianDateWithPersianNumbers(
//                        PersianDate.now()
//                );
        String persianTestMessage = " آزمایش در "
//                + formattedPersianDate
                ;

        String longPersianText = "\" کنفوسیوس\" بنیانگذار مکتب \" کنفوسیوس\" است. در بیش از دو هزار سال گذشته اندیشه مکتب \" کنفوسیوس \" نه تنها زمینه های سیاسی و فرهنگی را شامل شده بلکه در فعالیت ها و شیوه های فکری هر چینی تاثیرگذار بوده است. بعضی دانشمندان خارجی اندیشه مکتب \" کنفوسیوس\" را اندیشه مذهبی چین می دانند. در واقع مکتب \" کنفوسیوس\" یکی از مکتب های قدیمی چین است و اندیشه ای فلسفه است و مذهبی نمی باشد . اندیشه کنفوسیوس نه تنها در فرهنگ چین تاثیرگذاری عمیق دارد، بلکه در بعضی کشورهای آسیا تاثیر گذاشته است.";
        String longPersianTextA = "ایمان به حقیقت را هدف و مقصد اساسی زندگی خود قرار دادن و تکالیف را از روی وجدان به جا آوردن، طبع مرد را بلندتر و کاملتر می سازد. یکی را دوست داشتن و حیات او را آرزو کردن و دیگری را دشمن داشتن و مرگ او را خواستن، ناگزیر طبع آدمی را تاریک می سازد";
        String longPersianTextB = "اشتباه را درست نکردن، خود اشتباه دیگری است";
        String longPersianTextC = "اگر به راه خطا رفتی از برگشتن مترس";
        String longPersianTextD = "یک دقیقه موفـقیت، شکسـت چندین سالـه ی انسان را بی اثر کرده و جبران می کند";
        BindType bindTrx = BindType.BIND_TRX;
        BindParameter bindParameter = new BindParameter(
                bindTrx,
                userName,
                password,
                providedSystemType,
                TypeOfNumber.INTERNATIONAL,
                NumberingPlanIndicator.ISDN,
                null);


        try {
            SMPPSession smppSession = new SMPPSession(ipAddress, serverPortNumber, bindParameter);
            // SERVICE TYPE DATA
            //““ (NULL) Default
            //“CMT” Cellular Messaging
            //“CPT” Cellular Paging
            //“VMN” Voice Mail Notification
            //“VMA” Voice Mail Alerting
            //“WAP” Wireless Application Protocol
            //“USSD” Unstructured Supplementary Services Data

            smppSession.submitShortMessage(
                    null,
                    TypeOfNumber.ABBREVIATED,
                    NumberingPlanIndicator.ISDN,
                    sourcePhoneNumber,
                    TypeOfNumber.INTERNATIONAL,
                    NumberingPlanIndicator.ISDN,
                    destinationPhoneNumber,
                    esmClass,
                    (byte) 0,
                    (byte) 0,
                    null,
                    null,
                    registeredDelivery,
                    (byte) 1,
                    dataCoding,
                    (byte) 0,
                    persianTestMessage.getBytes()
            );


        } catch (IOException e) {
            System.out.println(" [  IOException  ] ");
            e.printStackTrace();
        } catch (ResponseTimeoutException e) {
            System.out.println(" [  ResponseTimeoutException  ] ");
            e.printStackTrace();
        } catch (PDUException e) {
            System.out.println(" [  PDUException  ] ");
            e.printStackTrace();
        } catch (InvalidResponseException e) {
            System.out.println(" [  InvalidResponseException  ] ");
            e.printStackTrace();
        } catch (NegativeResponseException e) {
            System.out.println(" [  NegativeResponseException  ] ");
            e.printStackTrace();
        }

//        log.info("bind successful ");
//        log.info("message : ");
//        log.info(persianTestMessage);


    }


}

//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//

class test {

    public static void main(String[] args) {
        ;
        String longPersianText = "\" کنفوسیوس\" بنیانگذار مکتب \" کنفوسیوس\" است. در بیش از دو هزار سال گذشته اندیشه مکتب \" کنفوسیوس \" نه تنها زمینه های سیاسی و فرهنگی را شامل شده بلکه در فعالیت ها و شیوه های فکری هر چینی تاثیرگذار بوده است. بعضی دانشمندان خارجی اندیشه مکتب \" کنفوسیوس\" را اندیشه مذهبی چین می دانند. در واقع مکتب \" کنفوسیوس\" یکی از مکتب های قدیمی چین است و اندیشه ای فلسفه است و مذهبی نمی باشد . اندیشه کنفوسیوس نه تنها در فرهنگ چین تاثیرگذاری عمیق دارد، بلکه در بعضی کشورهای آسیا تاثیر گذاشته است.";
        String longPersianTextA = "ایمان به حقیقت را هدف و مقصد اساسی زندگی خود قرار دادن و تکالیف را از روی وجدان به جا آوردن، طبع مرد را بلندتر و کاملتر می سازد. یکی را دوست داشتن و حیات او را آرزو کردن و دیگری را دشمن داشتن و مرگ او را خواستن، ناگزیر طبع آدمی را تاریک می سازد ";
        String longPersianTextB = "اشتباه را درست نکردن، خود اشتباه دیگری است ";
        String longPersianTextC = "اگر به راه خطا رفتی از برگشتن مترس ";
        String longPersianTextD = "یک دقیقه موفـقیت، شکسـت چندین سالـه ی انسان را بی اثر کرده و جبران می کند ";
        String messageBody = "Lorem ipsum dolor sit amet enim. Etiam ullamcorper. Suspendisse a pellentesque dui, non felis. Maecenas malesuada elit lectus felis, malesuada ultricies. Curabitur et ligula. Ut molestie a, ultricies porta urna.";

        String s =
                longPersianTextA +
                        longPersianTextB +
                        longPersianTextC +
                        longPersianTextD;
        System.out.println(messageBody.length());
    }
}


