/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jsmpp.examples;

import java.io.IOException;
import java.util.Date;
import java.util.Random;

import org.jsmpp.InvalidResponseException;
import org.jsmpp.PDUException;
import org.jsmpp.bean.*;
import org.jsmpp.extra.NegativeResponseException;
import org.jsmpp.extra.ResponseTimeoutException;
import org.jsmpp.session.BindParameter;
import org.jsmpp.session.SMPPSession;
import org.jsmpp.session.SubmitSmResult;
import org.jsmpp.util.AbsoluteTimeFormatter;
import org.jsmpp.util.TimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author uudashr
 */
public class SubmitLongMessageExample {
    public static final Logger log = LoggerFactory.getLogger(SubmitLongMessageExample.class);
    public static final TimeFormatter TIME_FORMATTER = new AbsoluteTimeFormatter();
    public static Random RANDOM = new Random();
    public static String userName = "YarangandmFara";
    public static String password = "Yar@GF51";
    public static String providedSystemType = "SMPP";
    public static String serverIpAddress = "10.200.114.15";
    public static String destinationPhoneNumber = "989209206311";
    public static String serverPortString = "2775";
    public static int serverPortNumber = 2775;
    public static String sourcePhoneNumber = "986369";
    public static BindType bindTrx = BindType.BIND_TRX;
    public static ESMClass esmClass = new ESMClass();
    public static DataCoding dataCoding = new GeneralDataCoding(Alphabet.ALPHA_8_BIT);
    public static String scheduleDeliveryTime = TIME_FORMATTER.format(new Date());
    public static RegisteredDelivery registeredDelivery = new RegisteredDelivery(SMSCDeliveryReceipt.DEFAULT);

    /// changer for rightel
    public static void main(String[] args) {
        try (
                SMPPSession session = new SMPPSession()
        ) {


//            new Tlv(
//                    SmppConstants.TAG_MESSAGE_PAYLOAD,
//                    CharsetUtil.encode(messageBody, CharsetUtil.CHARSET_UCS_2),
//                    "message_payload");
            byte[] bytes="hello".getBytes();
//            OptionalParameter aShort = new OptionalParameter.Payload_type(OptionalParameter.Tag.MESSAGE_PAYLOAD, bytes);
//            OptionalParameter aShort = new OptionalParameter.Payload_type(OptionalParameter.Tag.MESSAGE_PAYLOAD, bytes);

            session.connectAndBind(
                    serverIpAddress,
                    serverPortNumber,
                    new BindParameter(
                            bindTrx,
                            userName,
                            password,
                            providedSystemType,
                            TypeOfNumber.INTERNATIONAL,
                            NumberingPlanIndicator.ISDN,
                            null)
            );

            final int totalSegments = 3;

            // optional one
            OptionalParameter sarMsgRefNum = OptionalParameters
                    .newSarMsgRefNum(
                            (short) RANDOM.nextInt()
                    );

            // optional three
            OptionalParameter sarTotalSegments = OptionalParameters.newSarTotalSegments(
                    totalSegments
            );

            for (int counter = 0; counter < totalSegments; counter++) {

                final int seqNum =
                        counter + 1;

                String message =
                        "Message part " + seqNum + " of " + totalSegments + " ";

                // optional two
                OptionalParameter sarSegmentSeqnum = OptionalParameters.newSarSegmentSeqnum(
                        seqNum
                );

                /// call
                String messageId = submitMessage(
                        session,
                        message,
                        sarMsgRefNum,
                        sarSegmentSeqnum,
                        sarTotalSegments
                );

                log.info("Message submitted, message_id is {}", messageId);
            }

            session.unbind();

        } catch (IOException e) {
            log.error("Failed connect and bind to host", e);
        }
    }


    /// definition
    public static String submitMessage(
            SMPPSession session,
            String message,
            OptionalParameter sarMsgRefNum,
            OptionalParameter sarSegmentSeqNum,
            OptionalParameter sarTotalSegments
    ) {
        String messageId = null;
        try {
            String scheduleDeliveryTime = TIME_FORMATTER.format(new Date());
            SubmitSmResult submitSmResult =
                    session
                            .submitShortMessage(
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
                                    message.getBytes(),
                                    sarMsgRefNum,
                                    /*optional parameter*/ sarSegmentSeqNum,
                                    /*optional parameter*/ sarTotalSegments
                            );

            messageId = submitSmResult.getMessageId();

        } catch (PDUException e) {
            // Invalid PDU parameter
            log.error("Invalid PDU parameter", e);
        } catch (ResponseTimeoutException e) {
            // Response timeout
            log.error("Response timeout", e);
        } catch (InvalidResponseException e) {
            // Invalid response
            log.error("Receive invalid response", e);
        } catch (NegativeResponseException e) {
            // Receiving negative response (non-zero command_status)
            log.error("Receive negative response", e);
        } catch (IOException e) {
            log.error("I/O error occurred", e);
        }

        return messageId;
    }
}
