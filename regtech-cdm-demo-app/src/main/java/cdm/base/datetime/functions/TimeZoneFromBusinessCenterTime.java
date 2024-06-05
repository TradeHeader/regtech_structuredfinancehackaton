package cdm.base.datetime.functions;

import cdm.base.datetime.BusinessCenterTime;
import cdm.base.datetime.TimeZone;
import cdm.base.datetime.TimeZone.TimeZoneBuilder;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import java.util.Optional;
import javax.inject.Inject;


@ImplementedBy(TimeZoneFromBusinessCenterTime.TimeZoneFromBusinessCenterTimeDefault.class)
public abstract class TimeZoneFromBusinessCenterTime implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param time 
	* @return result 
	*/
	public TimeZone evaluate(BusinessCenterTime time) {
		TimeZone.TimeZoneBuilder resultBuilder = doEvaluate(time);
		
		final TimeZone result;
		if (resultBuilder == null) {
			result = null;
		} else {
			result = resultBuilder.build();
			objectValidator.validate(TimeZone.class, result);
		}
		
		return result;
	}

	protected abstract TimeZone.TimeZoneBuilder doEvaluate(BusinessCenterTime time);

	public static class TimeZoneFromBusinessCenterTimeDefault extends TimeZoneFromBusinessCenterTime {
		@Override
		protected TimeZone.TimeZoneBuilder doEvaluate(BusinessCenterTime time) {
			TimeZone.TimeZoneBuilder result = TimeZone.builder();
			return assignOutput(result, time);
		}
		
		protected TimeZone.TimeZoneBuilder assignOutput(TimeZone.TimeZoneBuilder result, BusinessCenterTime time) {
			return Optional.ofNullable(result)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
