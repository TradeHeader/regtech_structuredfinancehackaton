package cdm.product.asset.calculation.functions;

import cdm.product.common.schedule.CalculationPeriodBase;
import cdm.product.common.schedule.CalculationPeriodBase.CalculationPeriodBaseBuilder;
import cdm.product.common.schedule.CalculationPeriodData;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.records.Date;
import java.util.Optional;
import javax.inject.Inject;


@ImplementedBy(Create_CalculationPeriodBase.Create_CalculationPeriodBaseDefault.class)
public abstract class Create_CalculationPeriodBase implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param calcPeriodData A supplied CalculationPeriodData structure.
	* @return calcPeriod The corresponding CalculationPeriodBase structure.
	*/
	public CalculationPeriodBase evaluate(CalculationPeriodData calcPeriodData) {
		CalculationPeriodBase.CalculationPeriodBaseBuilder calcPeriodBuilder = doEvaluate(calcPeriodData);
		
		final CalculationPeriodBase calcPeriod;
		if (calcPeriodBuilder == null) {
			calcPeriod = null;
		} else {
			calcPeriod = calcPeriodBuilder.build();
			objectValidator.validate(CalculationPeriodBase.class, calcPeriod);
		}
		
		return calcPeriod;
	}

	protected abstract CalculationPeriodBase.CalculationPeriodBaseBuilder doEvaluate(CalculationPeriodData calcPeriodData);

	public static class Create_CalculationPeriodBaseDefault extends Create_CalculationPeriodBase {
		@Override
		protected CalculationPeriodBase.CalculationPeriodBaseBuilder doEvaluate(CalculationPeriodData calcPeriodData) {
			CalculationPeriodBase.CalculationPeriodBaseBuilder calcPeriod = CalculationPeriodBase.builder();
			return assignOutput(calcPeriod, calcPeriodData);
		}
		
		protected CalculationPeriodBase.CalculationPeriodBaseBuilder assignOutput(CalculationPeriodBase.CalculationPeriodBaseBuilder calcPeriod, CalculationPeriodData calcPeriodData) {
			calcPeriod
				.setAdjustedStartDate(MapperS.of(calcPeriodData).<Date>map("getStartDate", calculationPeriodData -> calculationPeriodData.getStartDate()).get());
			
			calcPeriod
				.setAdjustedEndDate(MapperS.of(calcPeriodData).<Date>map("getEndDate", calculationPeriodData -> calculationPeriodData.getEndDate()).get());
			
			return Optional.ofNullable(calcPeriod)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
