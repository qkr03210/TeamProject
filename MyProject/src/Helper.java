import java.util.regex.Pattern;

public class Helper {
	// RegularExpression------------------------------------------------------------------------
	// 길이 5~12의 영문, 숫자, (_)로 이루어진 아이디
	public static final Pattern VALID_ID_REGEX = Pattern.compile("^[A-Z]{1}[A-Z0-9_]{4,11}$", Pattern.CASE_INSENSITIVE);
	/*
	 * PASSWORD ^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$
	 * explain
	 * ^ # start-of-string
	 * (?=.*[0-9]) # a digit must occur at least once
	 * (?=.*[a-z]) # a lower case letter must occur at least once
	 * (?=.*[A-Z]) # an upper case letter must occur at least once
	 * (?=.*[@#$%^&+=]) # a special character must occur at least once
	 * (?=\S+$) # no whitespace allowed in the entire string 
	 * .{8,} # anything, at least eight places though 
	 * $ #end-of-string
	 */
	// 길이 최소 8자 이상의 영문, 숫자 혼합(특수문자 필수 아님, 공백 불가)
	public static final Pattern VALID_PASSWORD_REGEX = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$");
	// 010,011,016,017,018,019 + [.-] + 3~4자리 숫자 + [.-] 4자리 숫자
	public static final Pattern VALID_PHONE_NUMBER_REGEX = Pattern
			.compile("^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$");
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	public static boolean isID(String str) {
		if (str == null) {
			return false;
		}
		return VALID_ID_REGEX.matcher(str).matches();
	}

	public static boolean isPassword(String str) {
		// check(_ 메소드 써야함
		if (str == null) {
			return false;
		}
		return VALID_PASSWORD_REGEX.matcher(str).matches();
	}

	public static boolean isPhoneNumber(String str) {
		if (str == null) {
			return false;
		}
		return VALID_PHONE_NUMBER_REGEX.matcher(str).matches();
	}

	public static boolean isEmail(String str) {
		if (str == null) {
			return false;
		}
		return VALID_EMAIL_ADDRESS_REGEX.matcher(str).matches();
	}
	
	//------------------------------------------------------------------------------------------
}
