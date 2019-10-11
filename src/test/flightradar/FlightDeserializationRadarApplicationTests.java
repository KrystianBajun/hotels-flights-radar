package com.flightradar.flightradar;

import com.flightradar.flightradar.service.parser.CsvParser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlightDeserializationRadarApplicationTests {

	@Test
	public void airportTest() {

		CsvParser csvParser = new CsvParser();
				String airport = "Athens";
		//airlineParser.airlineParser(airport);

		Assert.assertEquals("ATH",csvParser.airportParser(airport));
	}

	@Test
	public void airlineTest() {

		CsvParser csvParser = new CsvParser();
		String airport = "LO";
		//airlineParser.airlineParser(airport);

		Assert.assertEquals("LOT Polish Airlines",csvParser.airlineParser(airport));
	}





}
