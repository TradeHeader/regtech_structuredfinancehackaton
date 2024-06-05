package cdm.observable.asset.validation.datarule;

import cdm.base.staticdata.party.AncillaryEntity;
import cdm.base.staticdata.party.LegalEntity;
import cdm.observable.asset.Money;
import cdm.observable.asset.ValuationMethod;
import cdm.observable.asset.ValuationSource;
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
@RosettaDataRule("ValuationMethodDealer")
@ImplementedBy(ValuationMethodDealer.Default.class)
public interface ValuationMethodDealer extends Validator<ValuationMethod> {
	
	String NAME = "ValuationMethodDealer";
	String DEFINITION = "if quotationAmount exists or minimumQuotationAmount exists then valuationSource -> dealerOrCCP -> legalEntity exists";
	
	ValidationResult<ValuationMethod> validate(RosettaPath path, ValuationMethod valuationMethod);
	
	class Default implements ValuationMethodDealer {
	
		@Override
		public ValidationResult<ValuationMethod> validate(RosettaPath path, ValuationMethod valuationMethod) {
			ComparisonResult result = executeDataRule(valuationMethod);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ValuationMethod", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "ValuationMethod", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(ValuationMethod valuationMethod) {
			try {
				if (exists(MapperS.of(valuationMethod).<Money>map("getQuotationAmount", _valuationMethod -> _valuationMethod.getQuotationAmount())).or(exists(MapperS.of(valuationMethod).<Money>map("getMinimumQuotationAmount", _valuationMethod -> _valuationMethod.getMinimumQuotationAmount()))).getOrDefault(false)) {
					return exists(MapperS.of(valuationMethod).<ValuationSource>map("getValuationSource", _valuationMethod -> _valuationMethod.getValuationSource()).<AncillaryEntity>map("getDealerOrCCP", valuationSource -> valuationSource.getDealerOrCCP()).<LegalEntity>map("getLegalEntity", ancillaryEntity -> ancillaryEntity.getLegalEntity()));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements ValuationMethodDealer {
	
		@Override
		public ValidationResult<ValuationMethod> validate(RosettaPath path, ValuationMethod valuationMethod) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ValuationMethod", path, DEFINITION);
		}
	}
}
