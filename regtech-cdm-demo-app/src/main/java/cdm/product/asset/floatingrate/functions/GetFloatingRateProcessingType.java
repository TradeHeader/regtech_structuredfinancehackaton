package cdm.product.asset.floatingrate.functions;

import cdm.base.staticdata.asset.rates.FloatingRateIndexEnum;
import cdm.base.staticdata.asset.rates.metafields.FieldWithMetaFloatingRateIndexEnum;
import cdm.observable.asset.FloatingRateOption;
import cdm.observable.asset.calculatedrate.FloatingRateCalculationParameters;
import cdm.observable.asset.fro.FloatingRateIndexCalculationDefaults;
import cdm.observable.asset.fro.FloatingRateIndexCalculationMethodEnum;
import cdm.observable.asset.fro.FloatingRateIndexCategoryEnum;
import cdm.observable.asset.fro.FloatingRateIndexDefinition;
import cdm.observable.asset.fro.FloatingRateIndexIdentification;
import cdm.observable.asset.fro.FloatingRateIndexStyleEnum;
import cdm.observable.asset.fro.functions.FloatingRateIndexMetadata;
import cdm.observable.asset.metafields.ReferenceWithMetaFloatingRateOption;
import cdm.product.asset.FloatingRateSpecification;
import cdm.product.asset.floatingrate.FloatingRateIndexProcessingTypeEnum;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(GetFloatingRateProcessingType.GetFloatingRateProcessingTypeDefault.class)
public abstract class GetFloatingRateProcessingType implements RosettaFunction {
	
	// RosettaFunction dependencies
	//
	@Inject protected FloatingRateIndexMetadata floatingRateIndexMetadata;

	/**
	* @param rateDef Specification details of the floating rate.
	* @return processingType The processing category for the rate .
	*/
	public FloatingRateIndexProcessingTypeEnum evaluate(FloatingRateSpecification rateDef) {
		FloatingRateIndexProcessingTypeEnum processingType = doEvaluate(rateDef);
		
		return processingType;
	}

	protected abstract FloatingRateIndexProcessingTypeEnum doEvaluate(FloatingRateSpecification rateDef);

	protected abstract MapperS<Boolean> isCalculatedRate(FloatingRateSpecification rateDef);

	protected abstract MapperS<? extends FloatingRateIndexDefinition> floatingRateDefinition(FloatingRateSpecification rateDef);

	protected abstract MapperS<? extends FloatingRateIndexCalculationDefaults> calcDefaults(FloatingRateSpecification rateDef);

	protected abstract MapperS<FloatingRateIndexCategoryEnum> category(FloatingRateSpecification rateDef);

	protected abstract MapperS<FloatingRateIndexStyleEnum> idxStyle(FloatingRateSpecification rateDef);

	protected abstract MapperS<FloatingRateIndexCalculationMethodEnum> method(FloatingRateSpecification rateDef);

	protected abstract MapperS<FloatingRateIndexProcessingTypeEnum> calcProcessingType(FloatingRateSpecification rateDef);

	protected abstract MapperS<FloatingRateIndexProcessingTypeEnum> definitionProcessingType(FloatingRateSpecification rateDef);

	protected abstract MapperS<FloatingRateIndexProcessingTypeEnum> processingCategory(FloatingRateSpecification rateDef);

	public static class GetFloatingRateProcessingTypeDefault extends GetFloatingRateProcessingType {
		@Override
		protected FloatingRateIndexProcessingTypeEnum doEvaluate(FloatingRateSpecification rateDef) {
			FloatingRateIndexProcessingTypeEnum processingType = null;
			return assignOutput(processingType, rateDef);
		}
		
		protected FloatingRateIndexProcessingTypeEnum assignOutput(FloatingRateIndexProcessingTypeEnum processingType, FloatingRateSpecification rateDef) {
			processingType = processingCategory(rateDef).get();
			
			return processingType;
		}
		
		@Override
		protected MapperS<Boolean> isCalculatedRate(FloatingRateSpecification rateDef) {
			return exists(MapperS.of(rateDef).<FloatingRateCalculationParameters>map("getCalculationParameters", floatingRate -> floatingRate.getCalculationParameters())).asMapper();
		}
		
		@Override
		protected MapperS<? extends FloatingRateIndexDefinition> floatingRateDefinition(FloatingRateSpecification rateDef) {
			return MapperS.of(floatingRateIndexMetadata.evaluate(MapperS.of(rateDef).<ReferenceWithMetaFloatingRateOption>map("getRateOption", floatingRateBase -> floatingRateBase.getRateOption()).<FloatingRateOption>map("getValue", _f->_f.getValue()).<FieldWithMetaFloatingRateIndexEnum>map("getFloatingRateIndex", floatingRateOption -> floatingRateOption.getFloatingRateIndex()).<FloatingRateIndexEnum>map("getValue", _f->_f.getValue()).get()));
		}
		
		@Override
		protected MapperS<? extends FloatingRateIndexCalculationDefaults> calcDefaults(FloatingRateSpecification rateDef) {
			return floatingRateDefinition(rateDef).<FloatingRateIndexCalculationDefaults>map("getCalculationDefaults", floatingRateIndexDefinition -> floatingRateIndexDefinition.getCalculationDefaults());
		}
		
		@Override
		protected MapperS<FloatingRateIndexCategoryEnum> category(FloatingRateSpecification rateDef) {
			return calcDefaults(rateDef).<FloatingRateIndexCategoryEnum>map("getCategory", floatingRateIndexCalculationDefaults -> floatingRateIndexCalculationDefaults.getCategory());
		}
		
		@Override
		protected MapperS<FloatingRateIndexStyleEnum> idxStyle(FloatingRateSpecification rateDef) {
			return calcDefaults(rateDef).<FloatingRateIndexStyleEnum>map("getIndexStyle", floatingRateIndexCalculationDefaults -> floatingRateIndexCalculationDefaults.getIndexStyle());
		}
		
		@Override
		protected MapperS<FloatingRateIndexCalculationMethodEnum> method(FloatingRateSpecification rateDef) {
			return calcDefaults(rateDef).<FloatingRateIndexCalculationMethodEnum>map("getMethod", floatingRateIndexCalculationDefaults -> floatingRateIndexCalculationDefaults.getMethod());
		}
		
		@Override
		protected MapperS<FloatingRateIndexProcessingTypeEnum> calcProcessingType(FloatingRateSpecification rateDef) {
			if (areEqual(idxStyle(rateDef), MapperS.of(FloatingRateIndexStyleEnum.COMPOUNDED_FRO), CardinalityOperator.All).and(areEqual(method(rateDef), MapperS.of(FloatingRateIndexCalculationMethodEnum.OIS_COMPOUND), CardinalityOperator.All)).getOrDefault(false)) {
				return MapperS.of(FloatingRateIndexProcessingTypeEnum.OIS);
			}
			if (areEqual(idxStyle(rateDef), MapperS.of(FloatingRateIndexStyleEnum.AVERAGE_FRO), CardinalityOperator.All).and(areEqual(method(rateDef), MapperS.of(FloatingRateIndexCalculationMethodEnum.AVERAGE), CardinalityOperator.All)).getOrDefault(false)) {
				return MapperS.of(FloatingRateIndexProcessingTypeEnum.OVERNIGHT_AVG);
			}
			return MapperS.<FloatingRateIndexProcessingTypeEnum>ofNull();
		}
		
		@Override
		protected MapperS<FloatingRateIndexProcessingTypeEnum> definitionProcessingType(FloatingRateSpecification rateDef) {
			if (areEqual(category(rateDef), MapperS.of(FloatingRateIndexCategoryEnum.SCREEN_RATE), CardinalityOperator.All).getOrDefault(false)) {
				return MapperS.of(FloatingRateIndexProcessingTypeEnum.SCREEN);
			}
			return calcProcessingType(rateDef);
		}
		
		@Override
		protected MapperS<FloatingRateIndexProcessingTypeEnum> processingCategory(FloatingRateSpecification rateDef) {
			if (areEqual(isCalculatedRate(rateDef), MapperS.of(true), CardinalityOperator.All).getOrDefault(false)) {
				return MapperS.of(FloatingRateIndexProcessingTypeEnum.MODULAR);
			}
			if (exists(floatingRateDefinition(rateDef).<FloatingRateIndexIdentification>map("getFro", floatingRateIndexDefinition -> floatingRateIndexDefinition.getFro())).getOrDefault(false)) {
				return definitionProcessingType(rateDef);
			}
			return MapperS.of(FloatingRateIndexProcessingTypeEnum.SCREEN);
		}
	}
}
