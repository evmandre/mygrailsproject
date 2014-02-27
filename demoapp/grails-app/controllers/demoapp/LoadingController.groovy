package demoapp

import com.dhtmlx.planner.DHXPlanner
import com.dhtmlx.planner.DHXSkin
import com.dhtmlx.planner.extensions.DHXExtension
import com.dhtmlx.planner.data.DHXDataFormat

class LoadingController {

	def loading_02() {
		DHXPlanner s = new DHXPlanner('./codebase/', DHXSkin.TERRACE);
		s.setInitialDate(2013, 1, 7);
		s.config.setScrollHour(8);
		s.setWidth(900);
		s.extensions.add(DHXExtension.RECURRING);
		s.load("./02_events", DHXDataFormat.JSON);

		def map = [
			planner: s.render(),
			sample_name: "Loading",
			sample_dsc: "Scheduler can load data of ICal, JSON, XML formats from a file or object. There is support for static and dynamic loading modes."
		]
		render(view: "/shared/article", model: map)
	}

	def events_02() {
		def evs = new CustomEventsManager(request);
		render evs.run();
	}
}
