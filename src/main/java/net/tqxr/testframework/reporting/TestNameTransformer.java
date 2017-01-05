package net.tqxr.testframework.reporting;

import net.tqxr.utilities.AnsiColorizer;

import static net.tqxr.utilities.AnsiColorizer.*;

class TestNameTransformer {

    String transformLowerCaseFirstLetterToUpperCase(String inputString) {

        // http://stackoverflow.com/a/33352947/1185717
        return Character.toUpperCase(inputString.charAt(0)) + inputString.substring(1);

    }

    String transformCamelTestNameToSpacedWords(String inputString) {


        // http://stackoverflow.com/a/2560017/1185717
        return inputString.replaceAll(
                String.format("%s|%s|%s",
                        "(?<=[A-Z])(?=[A-Z][a-z])",
                        "(?<=[^A-Z])(?=[A-Z])",
                        "(?<=[A-Za-z])(?=[^A-Za-z])"
                ),
                " "
        );

    }

    String transformTestName(String testName) {
        String upperFirstLetter = transformLowerCaseFirstLetterToUpperCase(testName);
        String spacedWords = transformCamelTestNameToSpacedWords(upperFirstLetter);
        return spacedWords;
    }


    String passFailSkip(boolean isFailed, boolean isIgnored, boolean isError) {
        AnsiColorizer.AsciiCode colouredString = GREEN;
        char statusChar = '\u2714'; // tick mark
        if (isFailed) {
            statusChar = '\u2718'; // cross
            colouredString = RED;
        }
        if (isIgnored) {
            statusChar = '\u2615'; // ☕ cute coffee cup :)
            colouredString = YELLOW;
        }
        if (isError) {
            statusChar = '\u2620'; // skull and crossbones
            colouredString = YELLOW.bright();
        }
        return String.format("%s%s%s ", colouredString, statusChar, RESET);
    }

}
