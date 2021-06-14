package wordchain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.util.Arrays;  
import java.util.List;  
import java.util.ArrayList;  
import java.util.Random;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.SpeechletException;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.OutputSpeech;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.SsmlOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;

public class WordChainSpeechlet implements Speechlet {
    
    private static final Logger log = LoggerFactory.getLogger(WordChainSpeechlet.class);

    private static List<String> VALID_WORDS;

    @Override
    public void onSessionStarted(final SessionStartedRequest request, final Session session)
        throws SpeechletException {
        log.info("onSessionStarted requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());
    }

    @Override
    public SpeechletResponse onLaunch(final LaunchRequest request, final Session session)
        throws SpeechletException {
        log.info("onLaunch requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());
        return getWelcomeResponse();
    }

    @Override
    public SpeechletResponse onIntent(final IntentRequest request, final Session session)
        throws SpeechletException {
        log.info("onIntent requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());

        Intent intent       = request.getIntent();
        String intentName   = intent.getName();

        populateValidWords();

        if ("PlayGame".equals(intentName))
            return playNewGame(intent, session);
        if ("ContinueGame".equals(intentName))
            return continueGame(intent, session);
        if ("RestartGame".equals(intentName))
            return restartGame(intent, session);
        if ("AMAZON.StartOverIntent".equals(intentName))
            return playNewGame(intent, session);
        if ("AMAZON.StopIntent".equals(intentName))
            return getByeResponse();
        if ("AMAZON.CancelIntent".equals(intentName))
            return getByeResponse();
        return getParseErrorResponse();
    }

    @Override
    public void onSessionEnded(final SessionEndedRequest request, final Session session)
        throws SpeechletException {
        log.info("onSessionEnded requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());
    }

    /**
    * Starts new game
    */
    private SpeechletResponse playNewGame(final Intent intent, final Session session) {
        String              word       = getRandomWord();
        String              output        = " Let us start a new game. I give you " + word;
        output                            = output + ". You tell "+WordChainConstants.CATEGORIES+" starting with " + word.substring(word.length()-1)+ ".";
        ArrayList<String>   word_done  = new ArrayList();
        word_done.add(word);
        session.setAttribute(WordChainConstants.ATT_INTERVAL, word_done);
        return showResponse(output);
    }

    /**
    * Continues current game
    */
    private SpeechletResponse continueGame(final Intent intent, final Session session) {
        Slot                slot            = intent.getSlot(WordChainConstants.SLOT_WORD);
        String              input           = slot.getValue().toLowerCase();

        // If the utterance contains null slot, or word is not in list of valid words
        if(slot == null || input == null || !VALID_WORDS.contains(input))
            return showResponse(WordChainConstants.INVALID_WORD);
        
        ArrayList<String>   word_done    = (ArrayList) session.getAttribute(WordChainConstants.ATT_INTERVAL);
        String              lastWord     = word_done.get(word_done.size()-1);

        // If the first letter of word (as said by user) is not equal to last letter of last word
        if(!lastWord.substring(lastWord.length()-1).equals(input.substring(0,1))) 
            return showResponse(WordChainConstants.INVALID_WORD);

        // If the cpuntry name was already spoken
        if(word_done.contains(input))
            return showResponse(WordChainConstants.ALREADY_DONE_WORD);

        // Add the word to list ofdone words
        word_done.add(input);

        // Computer's turn, choose a valid word
        String  word       = getWordWithLetter(word_done, input.substring(input.length()-1));
        String  output        = " I say " + word;
        output                = output + ". You say with " + word.substring(word.length()-1)+ ".";
        // Add the word to list of done words
        word_done.add(word);
        // Update session attributes
        session.setAttribute(WordChainConstants.ATT_INTERVAL, word_done);
        return showResponse(output);
    }    

     /**
    * Reset and start a new game
    */
    private SpeechletResponse restartGame(final Intent intent, final Session session) {

        ArrayList<String>   word_done    = (ArrayList) session.getAttribute(WordChainConstants.ATT_INTERVAL);
        word_done.clear();

        String              word       = getRandomWord();
        String              output        = " Ok let's restart. I give you " + word;
        output                            = output + ". You say with " + word.substring(word.length()-1)+ ".";

        word_done.add(word);
        session.setAttribute(WordChainConstants.ATT_INTERVAL, word_done);
        return showResponse(output);
    }    

    /**
    * Generates random word from the list of valid words
    */
    private String getRandomWord() {
        Random rand = new Random();
        int  n = rand.nextInt(VALID_WORDS.size()) + 1;
        return VALID_WORDS.get(n);
    }

    /**
    * Returns a coutry which is ot there in done words, and first letter is 'letter'
    */
    private String getWordWithLetter(ArrayList done, String letter) {
        for(int i=0;i<VALID_WORDS.size(); i++) {
            if(VALID_WORDS.get(i).substring(0,1).toLowerCase().equals(letter.toLowerCase()) 
                && !done.contains(VALID_WORDS.get(i)))
                return VALID_WORDS.get(i);
        }
        return null;
    }

    private SpeechletResponse getByeResponse() {
        PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
        outputSpeech.setText(WordChainConstants.GOODBYE_RESPONSE);
        return SpeechletResponse.newTellResponse(outputSpeech);
    }

    private SpeechletResponse getWelcomeResponse() {

        return newAskResponse(WordChainConstants.WELCOME_RESPONSE, false,
            WordChainConstants.WELCOME_REPROMPT, false, WordChainConstants.WELCOME_RESPONSE );
    }

  
    private SpeechletResponse getParseErrorResponse() {
        PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
        outputSpeech.setText(WordChainConstants.PARSE_ERROR);
        return SpeechletResponse.newTellResponse(outputSpeech);
    }

    private SpeechletResponse getHelpResponse() {
        return newAskResponse(WordChainConstants.HELP_RESPONSE, true,
            WordChainConstants.WELCOME_REPROMPT, false, WordChainConstants.HELP_RESPONSE);
    }

    private SpeechletResponse showResponse(String response) {
        return newAskResponse(response, false, response, false, response);
    }

   
    /** HELPER FUNCTIONS **/
    private void populateValidWords() {
        VALID_WORDS = Arrays.asList(WordChainConstants.words);
        // sort if you add any unsorted data
       // Collections.sort(VALID_WORDS);
        int size = VALID_WORDS.size();
        for(int i=0; i < size; i++) {
            VALID_WORDS.set(i, VALID_WORDS.get(i).toLowerCase());
        }
    }

   
    /**
     * Wrapper for creating the Ask response from the input strings with
     * plain text output and reprompt speeches.
     *
     * @param stringOutput
     *            the output to be spoken
     * @param repromptText
     *            the reprompt for if the user doesn't reply or is misunderstood.
     * @return SpeechletResponse the speechlet response
     */
    private SpeechletResponse newAskResponse(String stringOutput, String repromptText) {
        return newAskResponse(stringOutput, false, repromptText, false, stringOutput);
    }

    /**
     * Wrapper for creating the Ask response from the input strings.
     *
     * @param stringOutput
     *            the output to be spoken
     * @param isOutputSsml
     *            whether the output text is of type SSML
     * @param repromptText
     *            the reprompt for if the user doesn't reply or is misunderstood.
     * @param isRepromptSsml
     *            whether the reprompt text is of type SSML
     * @return SpeechletResponse the speechlet response
     */
    private SpeechletResponse newAskResponse(String stringOutput, boolean isOutputSsml,
            String repromptText, boolean isRepromptSsml, String cardText) {
        OutputSpeech outputSpeech, repromptOutputSpeech;
        SimpleCard card = new SimpleCard();
        if (isOutputSsml) {
            outputSpeech = new SsmlOutputSpeech();
            ((SsmlOutputSpeech) outputSpeech).setSsml(stringOutput);
        } else {
            outputSpeech = new PlainTextOutputSpeech();
            ((PlainTextOutputSpeech) outputSpeech).setText(stringOutput);
        }

        if (isRepromptSsml) {
            repromptOutputSpeech = new SsmlOutputSpeech();
            ((SsmlOutputSpeech) repromptOutputSpeech).setSsml(stringOutput);
        } else {
            repromptOutputSpeech = new PlainTextOutputSpeech();
            ((PlainTextOutputSpeech) repromptOutputSpeech).setText(repromptText);
        }
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(repromptOutputSpeech);

        card.setTitle(WordChainConstants.TITLE);
        card.setContent(cardText);

        return SpeechletResponse.newAskResponse(outputSpeech, reprompt,card);
    }
}