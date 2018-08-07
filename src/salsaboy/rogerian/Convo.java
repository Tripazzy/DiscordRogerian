package salsaboy.rogerian;

public class Convo {
    private static int rand(int max) {
        return (int) (Math.random() * max);
    }
    
    public static String previous = "";
    public static String[] topics = new String[] {
        "your family",
        "your job",
        "the weather"
    };
    
    public static String reply(String phrase) {
        boolean isQuestion = false;
        boolean isGreeting = false;
        
        phrase = phrase.toLowerCase();
        phrase = phrase.replace("'", "");
        phrase = phrase.replace(".", "");
        phrase = phrase.replace("!", "");
        phrase = phrase.replace(",", "");
        phrase = phrase.replace("i'm", "i am");
        if (phrase.startsWith("well ")) {
            phrase = phrase.replace("well ", "");
        }
        
        //Assist in comprehension
        if (phrase.endsWith("?")) {
            isQuestion = true;
        } else if (
            phrase.startsWith("who") ||
            phrase.startsWith("what") ||
            phrase.startsWith("where") ||
            phrase.startsWith("when") ||
            phrase.startsWith("why") ||
            phrase.startsWith("how") ||
            phrase.startsWith("is") ||
            phrase.startsWith("are") ||
            phrase.startsWith("maybe"))
        {
            isQuestion = true;
        }
        if (phrase.startsWith("hello") || phrase.startsWith("hi") || phrase.startsWith("gday") || phrase.startsWith("good morning") ||
            phrase.startsWith("good afternoon") || phrase.startsWith("good day")) {
            isGreeting = true;
        }
        
        String[] split = phrase.split(" ");
        
        //Use the extra info to construct a response
        String toRet;
        if (previous.endsWith("?")) {
            toRet = "Why do you think that?";
        } else {
            toRet = "Could you elaborate on that?";
        }
        
        if (phrase.replace("are", "do").contains("do you") || phrase.contains("can you") || phrase.contains("you are")) {
            toRet = "I think it would be better not to focus on me";
        } else if (isGreeting) {
            toRet = "Hello, what would you like to talk about?";
        } else if (isQuestion) {
            if (phrase.startsWith("maybe")) {
                toRet = "Why do you think that?";
            } else {
                toRet = "What do you think?";
            }
        } else if (phrase.startsWith("i am")) {
            if (previous.equals("Let's change the subject then\nTell me about your job")) {
                toRet = "Do you enjoy that?";
            }
        } else if (phrase.startsWith("because")) {
            toRet = "Does that convince you?";
        } else if (phrase.startsWith("i cant think of")) {
            toRet = "Let's change the subject then\nTell me about " + topics[rand(topics.length)];
        } else if (phrase.startsWith("i cant")) {
            if (previous.equals("Come on, see if you can think of something")
                || previous.startsWith( "Let's change the subject then\nTell me about ")) {
                toRet = "Let's talk about " + topics[rand(topics.length)] + " instead";
            }
        } else if (phrase.equals("i dont know")) {
            if (previous.equals("Come on, see if you can think of something")) {
                toRet = "Let's change the subject then\nTell me about " + topics[rand(topics.length)];
            } else {
                toRet = "Come on, see if you can think of something";
            }
        } else if (phrase.split(" but ").length > 1) {
            String clause2 = phrase.split("but")[1];
            String verb;
            if (clause2.startsWith(" my")) {
                verb = clause2.split(" ")[3].substring(0, clause2.split(" ")[3].length() - 1);
            } else {
                verb = clause2.split(" ")[2].substring(0, clause2.split(" ")[2].length() - 1);
            }
            
            String forRep = "Why do you think" + clause2.replace("my", "your")
                .replace(verb + "s", verb);
            
            toRet = forRep;
        }
        
        previous = toRet;
        return toRet;
    }
}
