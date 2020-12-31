package cn.np.boots.common.utils.type;

import cn.np.boots.common.api.NpJsonSerializeAction;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class NpDateUtils {


    public String format(Date date, String pattern, TimeZone timeZone, Locale locale) {
        return DateFormatUtils.format(date, pattern, timeZone, locale);
    }

    public String format(Date date, String pattern, TimeZone timeZone) {
        return this.format(date, pattern, timeZone, NpJsonSerializeAction.defaultLocal);
    }

    public String format(Date date, String pattern) {
        return this.format(date, pattern, NpJsonSerializeAction.defaultTimeZone);
    }

    public String format(Date date) {
        return this.format(date, NpJsonSerializeAction.defaultFormatPattern);
    }

    public Date tryParse(String dateString, Locale locale, String... parsePatterns) {
        try {
            return DateUtils.parseDate(dateString, locale, parsePatterns);
        } catch (Exception e) {
            return null;
        }
    }

    public Date tryParse(String dateString, String... parsePatterns) {
        try {
            return DateUtils.parseDate(dateString, NpJsonSerializeAction.defaultLocal, parsePatterns);
        } catch (Exception e) {
            return null;
        }
    }

    public Date tryParse(String dateString) {
        try {
            return DateUtils.parseDate(dateString, NpJsonSerializeAction.defaultLocal, NpJsonSerializeAction.defaultParsePattern);
        } catch (Exception e) {
            return null;
        }
    }
}
