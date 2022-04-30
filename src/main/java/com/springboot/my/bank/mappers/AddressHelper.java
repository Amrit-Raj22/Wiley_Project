/**
 * 
 */
package com.springboot.my.bank.mappers;

import com.springboot.my.bank.models.Address;

/**
 * @author Leona
 *
 */
public class AddressHelper {

	public static Address processAddress(String address) {
		String[] addressArray = address.split(",");

		return new Address(addressArray[0], addressArray[1], addressArray[2], addressArray[3],
				Integer.parseInt(addressArray[4].trim()));
	}

}
