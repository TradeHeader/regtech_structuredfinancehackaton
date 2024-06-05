package cdm.base.datetime.functions;

import cdm.base.datetime.AdjustableDate;
import cdm.base.datetime.AdjustableOrAdjustedOrRelativeDate;
import cdm.base.datetime.AdjustableOrAdjustedOrRelativeDate.AdjustableOrAdjustedOrRelativeDateBuilder;
import cdm.base.datetime.AdjustableOrRelativeDate;
import cdm.base.datetime.AdjustedRelativeDateOffset;
import cdm.base.datetime.BusinessCenters;
import cdm.base.datetime.BusinessDayAdjustments;
import cdm.base.datetime.BusinessDayConventionEnum;
import cdm.base.datetime.DayTypeEnum;
import cdm.base.datetime.PeriodEnum;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.records.Date;
import com.rosetta.model.metafields.FieldWithMetaDate;
import com.rosetta.model.metafields.ReferenceWithMetaDate;
import java.util.Optional;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(ConvertToAdjustableOrAdjustedOrRelativeDate.ConvertToAdjustableOrAdjustedOrRelativeDateDefault.class)
public abstract class ConvertToAdjustableOrAdjustedOrRelativeDate implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param adjustableOrRelativeDate 
	* @return adjustableOrAdjustedOrRelativeDate 
	*/
	public AdjustableOrAdjustedOrRelativeDate evaluate(AdjustableOrRelativeDate adjustableOrRelativeDate) {
		AdjustableOrAdjustedOrRelativeDate.AdjustableOrAdjustedOrRelativeDateBuilder adjustableOrAdjustedOrRelativeDateBuilder = doEvaluate(adjustableOrRelativeDate);
		
		final AdjustableOrAdjustedOrRelativeDate adjustableOrAdjustedOrRelativeDate;
		if (adjustableOrAdjustedOrRelativeDateBuilder == null) {
			adjustableOrAdjustedOrRelativeDate = null;
		} else {
			adjustableOrAdjustedOrRelativeDate = adjustableOrAdjustedOrRelativeDateBuilder.build();
			objectValidator.validate(AdjustableOrAdjustedOrRelativeDate.class, adjustableOrAdjustedOrRelativeDate);
		}
		
		return adjustableOrAdjustedOrRelativeDate;
	}

	protected abstract AdjustableOrAdjustedOrRelativeDate.AdjustableOrAdjustedOrRelativeDateBuilder doEvaluate(AdjustableOrRelativeDate adjustableOrRelativeDate);

	protected abstract MapperS<? extends AdjustedRelativeDateOffset> relativeDate(AdjustableOrRelativeDate adjustableOrRelativeDate);

	public static class ConvertToAdjustableOrAdjustedOrRelativeDateDefault extends ConvertToAdjustableOrAdjustedOrRelativeDate {
		@Override
		protected AdjustableOrAdjustedOrRelativeDate.AdjustableOrAdjustedOrRelativeDateBuilder doEvaluate(AdjustableOrRelativeDate adjustableOrRelativeDate) {
			AdjustableOrAdjustedOrRelativeDate.AdjustableOrAdjustedOrRelativeDateBuilder adjustableOrAdjustedOrRelativeDate = AdjustableOrAdjustedOrRelativeDate.builder();
			return assignOutput(adjustableOrAdjustedOrRelativeDate, adjustableOrRelativeDate);
		}
		
		protected AdjustableOrAdjustedOrRelativeDate.AdjustableOrAdjustedOrRelativeDateBuilder assignOutput(AdjustableOrAdjustedOrRelativeDate.AdjustableOrAdjustedOrRelativeDateBuilder adjustableOrAdjustedOrRelativeDate, AdjustableOrRelativeDate adjustableOrRelativeDate) {
			adjustableOrAdjustedOrRelativeDate
				.setAdjustedDateValue(MapperS.of(adjustableOrRelativeDate).<AdjustableDate>map("getAdjustableDate", _adjustableOrRelativeDate -> _adjustableOrRelativeDate.getAdjustableDate()).<FieldWithMetaDate>map("getAdjustedDate", adjustableDate -> adjustableDate.getAdjustedDate()).<Date>map("getValue", _f->_f.getValue()).get());
			
			adjustableOrAdjustedOrRelativeDate
				.setUnadjustedDate(MapperS.of(adjustableOrRelativeDate).<AdjustableDate>map("getAdjustableDate", _adjustableOrRelativeDate -> _adjustableOrRelativeDate.getAdjustableDate()).<Date>map("getUnadjustedDate", adjustableDate -> adjustableDate.getUnadjustedDate()).get());
			
			adjustableOrAdjustedOrRelativeDate
				.setDateAdjustments(MapperS.of(adjustableOrRelativeDate).<AdjustableDate>map("getAdjustableDate", _adjustableOrRelativeDate -> _adjustableOrRelativeDate.getAdjustableDate()).<BusinessDayAdjustments>map("getDateAdjustments", adjustableDate -> adjustableDate.getDateAdjustments()).get());
			
			Date ifThenElseResult0 = null;
			if (exists(relativeDate(adjustableOrRelativeDate)).getOrDefault(false)) {
				ifThenElseResult0 = relativeDate(adjustableOrRelativeDate).<Date>map("getAdjustedDate", relativeDateOffset -> relativeDateOffset.getAdjustedDate()).get();
			}
			adjustableOrAdjustedOrRelativeDate
				.getOrCreateRelativeDate()
				.setAdjustedDate(ifThenElseResult0);
			
			BusinessCenters ifThenElseResult1 = null;
			if (exists(relativeDate(adjustableOrRelativeDate)).getOrDefault(false)) {
				ifThenElseResult1 = relativeDate(adjustableOrRelativeDate).<BusinessCenters>map("getBusinessCenters", relativeDateOffset -> relativeDateOffset.getBusinessCenters()).get();
			}
			adjustableOrAdjustedOrRelativeDate
				.getOrCreateRelativeDate()
				.setBusinessCenters(ifThenElseResult1);
			
			BusinessDayConventionEnum ifThenElseResult2 = null;
			if (exists(relativeDate(adjustableOrRelativeDate)).getOrDefault(false)) {
				ifThenElseResult2 = relativeDate(adjustableOrRelativeDate).<BusinessDayConventionEnum>map("getBusinessDayConvention", relativeDateOffset -> relativeDateOffset.getBusinessDayConvention()).get();
			}
			adjustableOrAdjustedOrRelativeDate
				.getOrCreateRelativeDate()
				.setBusinessDayConvention(ifThenElseResult2);
			
			Date ifThenElseResult3 = null;
			if (exists(relativeDate(adjustableOrRelativeDate)).getOrDefault(false)) {
				ifThenElseResult3 = relativeDate(adjustableOrRelativeDate).<ReferenceWithMetaDate>map("getDateRelativeTo", relativeDateOffset -> relativeDateOffset.getDateRelativeTo()).<Date>map("getValue", _f->_f.getValue()).get();
			}
			adjustableOrAdjustedOrRelativeDate
				.getOrCreateRelativeDate()
				.setDateRelativeToValue(ifThenElseResult3);
			
			DayTypeEnum ifThenElseResult4 = null;
			if (exists(relativeDate(adjustableOrRelativeDate)).getOrDefault(false)) {
				ifThenElseResult4 = relativeDate(adjustableOrRelativeDate).<DayTypeEnum>map("getDayType", offset -> offset.getDayType()).get();
			}
			adjustableOrAdjustedOrRelativeDate
				.getOrCreateRelativeDate()
				.setDayType(ifThenElseResult4);
			
			PeriodEnum ifThenElseResult5 = null;
			if (exists(relativeDate(adjustableOrRelativeDate)).getOrDefault(false)) {
				ifThenElseResult5 = relativeDate(adjustableOrRelativeDate).<PeriodEnum>map("getPeriod", period -> period.getPeriod()).get();
			}
			adjustableOrAdjustedOrRelativeDate
				.getOrCreateRelativeDate()
				.setPeriod(ifThenElseResult5);
			
			Integer ifThenElseResult6 = null;
			if (exists(relativeDate(adjustableOrRelativeDate)).getOrDefault(false)) {
				ifThenElseResult6 = relativeDate(adjustableOrRelativeDate).<Integer>map("getPeriodMultiplier", period -> period.getPeriodMultiplier()).get();
			}
			adjustableOrAdjustedOrRelativeDate
				.getOrCreateRelativeDate()
				.setPeriodMultiplier(ifThenElseResult6);
			
			return Optional.ofNullable(adjustableOrAdjustedOrRelativeDate)
				.map(o -> o.prune())
				.orElse(null);
		}
		
		@Override
		protected MapperS<? extends AdjustedRelativeDateOffset> relativeDate(AdjustableOrRelativeDate adjustableOrRelativeDate) {
			return MapperS.of(adjustableOrRelativeDate).<AdjustedRelativeDateOffset>map("getRelativeDate", _adjustableOrRelativeDate -> _adjustableOrRelativeDate.getRelativeDate());
		}
	}
}
