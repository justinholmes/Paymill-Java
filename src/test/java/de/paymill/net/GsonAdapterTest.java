package de.paymill.net;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

import com.google.gson.Gson;

import de.paymill.model.Offer;
import de.paymill.model.Offer.Interval;

public class GsonAdapterTest {

	@Test
	public void testDateSerialization() {
		GsonAdapter adapter = new GsonAdapter();
		Gson gson = adapter.createGson();

		Date date = new Date();
		String time = Integer.toString((int) (date.getTime() / 1000));
		assertEquals(time, gson.toJson(date));
	}
	
	@Test
	public void testDateDeserialization() {
		GsonAdapter adapter = new GsonAdapter();
		Gson gson = adapter.createGson();

		Date date = new Date(12345000);
		TestObj obj = gson.fromJson("{\"some_date\":12345}", TestObj.class);
		
		assertEquals(date, obj.getSomeDate());
	}
	
	@Test
	public void testCamelCase() {
		GsonAdapter adapter = new GsonAdapter();
		Gson gson = adapter.createGson();
		
		Offer offer = new Offer();
		offer.setAmount(199);
		offer.setInterval(Interval.WEEK);
		offer.setName("Superabo");
		offer.setTrialPeriodDays(15);
		
		String json = gson.toJson(offer);
		assertEquals("{\"name\":\"Superabo\",\"amount\":199,\"interval\":\"week\",\"trial_period_days\":15}", json);
	}

	class TestObj {
		private Date someDate;

		public Date getSomeDate() {
			return someDate;
		}

		public void setSomeDate(Date someDate) {
			this.someDate = someDate;
		}

	}
}
