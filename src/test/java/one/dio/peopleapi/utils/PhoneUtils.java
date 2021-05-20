package one.dio.peopleapi.utils;

import one.dio.peopleapi.dto.request.PhoneDTO;
import one.dio.peopleapi.entity.Phone;
import one.dio.peopleapi.enums.PhoneType;

public class PhoneUtils {
	private static final String PHONE_NUMBER = "62991237456";
	private static final PhoneType PHONE_TYPE = PhoneType.MOBILE;
	private static final Long PHONE_ID = 1L;
	
	public static PhoneDTO createFakeDTO() {
		return PhoneDTO.builder()
				.number(PHONE_NUMBER)
				.type(PHONE_TYPE)
				.build();
	}
	
	public static Phone createFakeEntity() {
		return Phone.builder()
				.id(PHONE_ID)
				.number(PHONE_NUMBER)
				.type(PHONE_TYPE)
				.build();
	}
	
}
