package enumeration;

import java.util.Locale;

/**
 * Created by Marin on 20.05.2017.
 */
public enum TranslationEnum {

    ENGLISH("en", "EN"),
    GERMAN("de", "GE");

    private String languageCode;

    private String countryCode;

    TranslationEnum(String languageCode, String countryCode){
        this.languageCode = languageCode;
        this.countryCode = countryCode;
    }

    public String getLanguageCode(){
        return this.languageCode;
    }

    public String getCountryCode(){
        return this.languageCode;
    }

    public static Locale getLocale(String localeEnum){
        Locale locale;
        switch (localeEnum) {

            case "EN":
                locale = new Locale(TranslationEnum.ENGLISH.getLanguageCode(), TranslationEnum.ENGLISH.getCountryCode());
                break;
            case "GE":
                locale = new Locale(TranslationEnum.GERMAN.getLanguageCode(), TranslationEnum.GERMAN.getCountryCode());
                break;
            default:
                locale = new Locale(TranslationEnum.ENGLISH.getLanguageCode(), TranslationEnum.ENGLISH.getCountryCode());
        }

        return locale;
    }
}
