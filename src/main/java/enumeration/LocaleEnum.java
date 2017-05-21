package enumeration;

import java.util.Locale;

/**
 * Created by Marin on 20.05.2017.
 */
public enum  LocaleEnum {

    ENGLISH("EN"),
    GERMAN("GE");

    private String locale;

    LocaleEnum(String locale) {
        this.locale = locale;
    }

    public String getLocale() {
        return this.locale;
    }

    public static Locale getLocale(LocaleEnum localeEnum){
        Locale locale;
        switch (localeEnum) {

            case ENGLISH:
                locale = new Locale(TranslationEnum.ENGLISH.getLanguageCode(), TranslationEnum.ENGLISH.getCountryCode());
                break;
            case GERMAN:
                locale = new Locale(TranslationEnum.GERMAN.getLanguageCode(), TranslationEnum.GERMAN.getCountryCode());
                break;
            default:
                locale = new Locale(TranslationEnum.ENGLISH.getLanguageCode(), TranslationEnum.ENGLISH.getCountryCode());
                break;
        }

        return locale;
    }
}
