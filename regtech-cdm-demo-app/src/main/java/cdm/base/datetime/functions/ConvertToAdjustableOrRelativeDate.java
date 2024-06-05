package cdm.base.datetime.functions;

import cdm.base.datetime.AdjustableOrAdjustedOrRelativeDate;
import cdm.base.datetime.AdjustableOrRelativeDate;
import cdm.base.datetime.AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder;
import cdm.base.datetime.BusinessCenters;
import cdm.base.datetime.BusinessDayAdjustments;
import cdm.base.datetime.BusinessDayConventionEnum;
import cdm.base.datetime.DayTypeEnum;
import cdm.base.datetime.PeriodEnum;
import cdm.base.datetime.RelativeDateOffset;
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

@ImplementedBy(ConvertToAdjustableOrRelativeDate.ConvertToAdjustableOrRelativeDateDefault.class)
public abstract class ConvertToAdjustableOrRelativeDate implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param adjustableOrAdjustedOrRelativeDate 
	* @return adjustableOrRelativeDate 
	*/
	public AdjustableOrRelativeDate evaluate(AdjustableOrAdjustedOrRelativeDate adjustableOrAdjustedOrRelativeDate) {
		AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder adjustableOrRelativeDateBuilder = doEvaluate(adjustableOrAdjustedOrRelativeDate);
		
		final AdjustableOrRelativeDate adjustableOrRelativeDate;
		if (adjustableOrRelativeDateBuilder == null) {
			adjustableOrRelativeDate = null;
		} else {
			adjustableOrRelativeDate = adjustableOrRelativeDateBuilder.build();
			objectValidator.validate(AdjustableOrRelativeDate.class, adjustableOrRelativeDate);
		}
		
		return adjustableOrRelativeDate;
	}

	protected abstract AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder doEvaluate(AdjustableOrAdjustedOrRelativeDate adjustableOrAdjustedOrRelativeDate);

	protected abstract MapperS<? extends RelativeDateOffset> relativeDate(AdjustableOrAdjustedOrRelativeDate adjustableOrAdjustedOrRelativeDate);

	public static class ConvertToAdjustableOrRelativeDateDefault extends ConvertToAdjustableOrRelativeDate {
		@Override
		protected AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder doEvaluate(AdjustableOrAdjustedOrRelativeDate adjustableOrAdjustedOrRelativeDate) {
			AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder adjustableOrRelativeDate = AdjustableOrRelativeDate.builder();
			return assignOutput(adjustableOrRelativeDate, adjustableOrAdjustedOrRelativeDate);
		}
		
		protected AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder assignOutput(AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder adjustableOrRelativeDate, AdjustableOrAdjustedOrRelativeDate adjustableOrAdjustedOrRelativeDate) {
			adjustableOrRelativeDate
				.getOrCreateAdjustableDate()
				.setAdjustedDateValue(MapperS.of(adjustableOrAdjustedOrRelativeDate).<FieldWithMetaDate>map("getAdjustedDate", _adjustableOrAdjustedOrRelativeDate -> _adjustableOrAdjustedOrRelativeDate.getAdjustedDate()).<Date>map("getValue", _f->_f.getValue()).get());
			
			adjustableOrRelativeDate
				.getOrCreateAdjustableDate()
				.setUnadjustedDate(MapperS.of(adjustableOrAdjustedOrRelativeDate).<Date>map("getUnadjustedDate", _adjustableOrAdjustedOrRelativeDate -> _adjustableOrAdjustedOrRelativeDate.getUnadjustedDate()).get());
			
			adjustableOrRelativeDate
				.getOrCreateAdjustableDate()
				.setDateAdjustments(MapperS.of(adjustableOrAdjustedOrRelativeDate).<BusinessDayAdjustments>map("getDateAdjustments", _adjustableOrAdjustedOrRelativeDate -> _adjustableOrAdjustedOrRelativeDate.getDateAdjustments()).get());
			
			Date ifThenElseResult0 = null;
			if (exists(relativeDate(adjustableOrAdjustedOrRelativeDate)).getOrDefault(false)) {
				ifThenElseResult0 = relativeDate(adjustableOrAdjustedOrRelativeDate).<Date>map("getAdjustedDate", relativeDateOffset -> relativeDateOffset.getAdjustedDate()).get();
			}
			adjustableOrRelativeDate
				.getOrCreateRelativeDate()
				.setAdjustedDate(ifThenElseResult0);
			
			BusinessCenters ifThenElseResult1 = null;
			if (exists(relativeDate(adjustableOrAdjustedOrRelativeDate)).getOrDefault(false)) {
				ifThenElseResult1 = relativeDate(adjustableOrAdjustedOrRelativeDate).<BusinessCenters>map("getBusinessCenters", relativeDateOffset -> relativeDateOffset.getBusinessCenters()).get();
			}
			adjustableOrRelativeDate
				.getOrCreateRelativeDate()
				.setBusinessCenters(ifThenElseResult1);
			
			BusinessDayConventionEnum ifThenElseResult2 = null;
			if (exists(relativeDate(adjustableOrAdjustedOrRelativeDate)).getOrDefault(false)) {
				ifThenElseResult2 = relativeDate(adjustableOrAdjustedOrRelativeDate).<BusinessDayConventionEnum>map("getBusinessDayConvention", relativeDateOffset -> relativeDateOffset.getBusinessDayConvention()).get();
			}
			adjustableOrRelativeDate
				.getOrCreateRelativeDate()
				.setBusinessDayConvention(ifThenElseResult2);
			
			Date ifThenElseResult3 = null;
			if (exists(relativeDate(adjustableOrAdjustedOrRelativeDate)).getOrDefault(false)) {
				ifThenElseResult3 = relativeDate(adjustableOrAdjustedOrRelativeDate).<ReferenceWithMetaDate>map("getDateRelativeTo", relativeDateOffset -> relativeDateOffset.getDateRelativeTo()).<Date>map("getValue", _f->_f.getValue()).get();
			}
			adjustableOrRelativeDate
				.getOrCreateRelativeDate()
				.setDateRelativeToValue(ifThenElseResult3);
			
			DayTypeEnum ifThenElseResult4 = null;
			if (exists(relativeDate(adjustableOrAdjustedOrRelativeDate)).getOrDefault(false)) {
				ifThenElseResult4 = relativeDate(adjustableOrAdjustedOrRelativeDate).<DayTypeEnum>map("getDayType", offset -> offset.getDayType()).get();
			}
			adjustableOrRelativeDate
				.getOrCreateRelativeDate()
				.setDayType(ifThenElseResult4);
			
			PeriodEnum ifThenElseResult5 = null;
			if (exists(relativeDate(adjustableOrAdjustedOrRelativeDate)).getOrDefault(false)) {
				ifThenElseResult5 = relativeDate(adjustableOrAdjustedOrRelativeDate).<PeriodEnum>map("getPeriod", period -> period.getPeriod()).get();
			}
			adjustableOrRelativeDate
				.getOrCreateRelativeDate()
				.setPeriod(ifThenElseResult5);
			
			Integer ifThenElseResult6 = null;
			if (exists(relativeDate(adjustableOrAdjustedOrRelativeDate)).getOrDefault(false)) {
				ifThenElseResult6 = relativeDate(adjustableOrAdjustedOrRelativeDate).<Integer>map("getPeriodMultiplier", period -> period.getPeriodMultiplier()).get();
			}
			adjustableOrRelativeDate
				.getOrCreateRelativeDate()
				.setPeriodMultiplier(ifThenElseResult6);
			
			return Optional.ofNullable(adjustableOrRelativeDate)
				.map(o -> o.prune())
				.orElse(null);
		}
		
		@Override
		protected MapperS<? extends RelativeDateOffset> relativeDate(AdjustableOrAdjustedOrRelativeDate adjustableOrAdjustedOrRelativeDate) {
			return MapperS.of(adjustableOrAdjustedOrRelativeDate).<RelativeDateOffset>map("getRelativeDate", _adjustableOrAdjustedOrRelativeDate -> _adjustableOrAdjustedOrRelativeDate.getRelativeDate());
		}
	}
}
