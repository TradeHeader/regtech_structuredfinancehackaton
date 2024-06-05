package cdm.base.math.functions;

import cdm.base.math.CompareOp;
import cdm.base.math.Quantity;
import cdm.base.math.UnitType;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperListOfLists;
import com.rosetta.model.lib.mapper.MapperS;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(CompareQuantityByUnitOfAmount.CompareQuantityByUnitOfAmountDefault.class)
public abstract class CompareQuantityByUnitOfAmount implements RosettaFunction {
	
	// RosettaFunction dependencies
	//
	@Inject protected CompareNumbers compareNumbers;
	@Inject protected FilterQuantity filterQuantity;

	/**
	* @param quantity1 
	* @param op 
	* @param quantity2 
	* @param unitOfAmount 
	* @return result 
	*/
	public Boolean evaluate(List<? extends Quantity> quantity1, CompareOp op, List<? extends Quantity> quantity2, UnitType unitOfAmount) {
		Boolean result = doEvaluate(quantity1, op, quantity2, unitOfAmount);
		
		return result;
	}

	protected abstract Boolean doEvaluate(List<? extends Quantity> quantity1, CompareOp op, List<? extends Quantity> quantity2, UnitType unitOfAmount);

	public static class CompareQuantityByUnitOfAmountDefault extends CompareQuantityByUnitOfAmount {
		@Override
		protected Boolean doEvaluate(List<? extends Quantity> quantity1, CompareOp op, List<? extends Quantity> quantity2, UnitType unitOfAmount) {
			if (quantity1 == null) {
				quantity1 = Collections.emptyList();
			}
			if (quantity2 == null) {
				quantity2 = Collections.emptyList();
			}
			Boolean result = null;
			return assignOutput(result, quantity1, op, quantity2, unitOfAmount);
		}
		
		protected Boolean assignOutput(Boolean result, List<? extends Quantity> quantity1, CompareOp op, List<? extends Quantity> quantity2, UnitType unitOfAmount) {
			final MapperListOfLists<Boolean> thenResult = MapperC.<Quantity>of(filterQuantity.evaluate(quantity1, unitOfAmount))
				.mapItemToList(q1 -> MapperC.<Quantity>of(filterQuantity.evaluate(quantity2, unitOfAmount))
					.mapItem(q2 -> MapperS.of(compareNumbers.evaluate(q1.<BigDecimal>map("getValue", measureBase -> measureBase.getValue()).get(), op, q2.<BigDecimal>map("getValue", measureBase -> measureBase.getValue()).get()))));
			result = areEqual(thenResult
				.flattenList(), MapperS.of(true), CardinalityOperator.All).asMapper().get();
			
			return result;
		}
	}
}
