package demoapp

import com.dhtmlx.planner.DHXEv
import com.dhtmlx.planner.DHXEventsManager
import com.dhtmlx.planner.DHXStatus
import demoapp.Event


class CustomEventsManager extends DHXEventsManager {

	def CustomEventsManager(request) {
		super(request);
	}

	@Override
	def Iterable<Event> getEvents() {
		return Event.list();
	}

	@Override
	def DHXStatus saveEvent(DHXEv event, DHXStatus status) {
		def ev = (Event) event;
		if (status == DHXStatus.UPDATE)
			event.save();
		else if (status == DHXStatus.DELETE)
			event.delete();
		else if (status == DHXStatus.INSERT)
			event.save();

		return status;
	}


	@Override
	def DHXEv createEvent(String id, DHXStatus status) {
		if (status == DHXStatus.INSERT)
			return new Event();
		else
			return Event.get(id);
	}

}

