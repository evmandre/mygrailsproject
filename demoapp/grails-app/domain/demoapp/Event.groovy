package demoapp

import com.dhtmlx.planner.DHXEvent

class Event extends DHXEvent{

	static mapping = {
		table 'events'
		id column: 'event_id'
		text column: 'event_name'
	}

}
