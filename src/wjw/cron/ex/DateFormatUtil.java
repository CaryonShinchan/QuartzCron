package wjw.cron.ex;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * ʹ��ThreadLocal�Կռ任ʱ����SimpleDateFormat�̰߳�ȫ���⡣
 * 
 * @author wstone
 */
public abstract class DateFormatUtil {

  private static final ThreadLocal<Map<String, DateFormat>> _threadLocal = new ThreadLocal<Map<String, DateFormat>>() {

    protected Map<String, DateFormat> initialValue() {
      return new HashMap<String, DateFormat>();
    }
  };

  public static DateFormat getDateFormat(String pattern) {
    DateFormat dateFormat = _threadLocal.get().get(pattern);
    if (dateFormat == null) {
      dateFormat = new SimpleDateFormat(pattern);
      _threadLocal.get().put(pattern, dateFormat);
    }
    return dateFormat;
  }

  public static Date parse(String pattern, String textDate) throws ParseException {
    return getDateFormat(pattern).parse(textDate);
  }

  public static String format(String pattern, Date date) {
    return getDateFormat(pattern).format(date);
  }
}
