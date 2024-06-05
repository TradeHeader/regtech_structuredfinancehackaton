package cdm.event.common.validation.datarule;

import cdm.base.math.NonNegativeQuantitySchedule;
import cdm.base.math.metafields.FieldWithMetaNonNegativeQuantitySchedule;
import cdm.event.common.IndexTransitionInstruction;
import cdm.observable.asset.FloatingRateOption;
import cdm.observable.asset.Observable;
import cdm.observable.asset.PriceSchedule;
import cdm.observable.asset.PriceTypeEnum;
import cdm.observable.asset.metafields.FieldWithMetaFloatingRateOption;
import cdm.observable.asset.metafields.FieldWithMetaPriceSchedule;
import cdm.product.common.settlement.PriceQuantity;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

/**
 * @version ${project.version}
 */
@RosettaDataRule("IndexTransitionInstructionPriceQuantity")
@ImplementedBy(IndexTransitionInstructionPriceQuantity.Default.class)
public interface IndexTransitionInstructionPriceQuantity extends Validator<IndexTransitionInstruction> {
	
	String NAME = "IndexTransitionInstructionPriceQuantity";
	String DEFINITION = "priceQuantity -> price -> priceType contains PriceTypeEnum -> InterestRate and priceQuantity -> observable -> rateOption exists and priceQuantity -> quantity is absent";
	
	ValidationResult<IndexTransitionInstruction> validate(RosettaPath path, IndexTransitionInstruction indexTransitionInstruction);
	
	class Default implements IndexTransitionInstructionPriceQuantity {
	
		@Override
		public ValidationResult<IndexTransitionInstruction> validate(RosettaPath path, IndexTransitionInstruction indexTransitionInstruction) {
			ComparisonResult result = executeDataRule(indexTransitionInstruction);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "IndexTransitionInstruction", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "IndexTransitionInstruction", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(IndexTransitionInstruction indexTransitionInstruction) {
			try {
				return contains(MapperS.of(indexTransitionInstruction).<PriceQuantity>mapC("getPriceQuantity", _indexTransitionInstruction -> _indexTransitionInstruction.getPriceQuantity()).<FieldWithMetaPriceSchedule>mapC("getPrice", priceQuantity -> priceQuantity.getPrice()).<PriceSchedule>map("getValue", _f->_f.getValue()).<PriceTypeEnum>map("getPriceType", priceSchedule -> priceSchedule.getPriceType()), MapperS.of(PriceTypeEnum.INTEREST_RATE)).and(exists(MapperS.of(indexTransitionInstruction).<PriceQuantity>mapC("getPriceQuantity", _indexTransitionInstruction -> _indexTransitionInstruction.getPriceQuantity()).<Observable>map("getObservable", priceQuantity -> priceQuantity.getObservable()).<FieldWithMetaFloatingRateOption>map("getRateOption", observable -> observable.getRateOption()).<FloatingRateOption>map("getValue", _f->_f.getValue()))).and(notExists(MapperS.of(indexTransitionInstruction).<PriceQuantity>mapC("getPriceQuantity", _indexTransitionInstruction -> _indexTransitionInstruction.getPriceQuantity()).<FieldWithMetaNonNegativeQuantitySchedule>mapC("getQuantity", priceQuantity -> priceQuantity.getQuantity()).<NonNegativeQuantitySchedule>map("getValue", _f->_f.getValue())));
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements IndexTransitionInstructionPriceQuantity {
	
		@Override
		public ValidationResult<IndexTransitionInstruction> validate(RosettaPath path, IndexTransitionInstruction indexTransitionInstruction) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "IndexTransitionInstruction", path, DEFINITION);
		}
	}
}
