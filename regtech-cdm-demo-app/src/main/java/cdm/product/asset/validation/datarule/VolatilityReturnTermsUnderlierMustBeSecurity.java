package cdm.product.asset.validation.datarule;

import cdm.base.staticdata.asset.common.ProductIdentifier;
import cdm.base.staticdata.asset.common.metafields.FieldWithMetaProductIdentifier;
import cdm.observable.asset.Observable;
import cdm.product.asset.VolatilityReturnTerms;
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
@RosettaDataRule("VolatilityReturnTermsUnderlierMustBeSecurity")
@ImplementedBy(VolatilityReturnTermsUnderlierMustBeSecurity.Default.class)
public interface VolatilityReturnTermsUnderlierMustBeSecurity extends Validator<VolatilityReturnTerms> {
	
	String NAME = "VolatilityReturnTermsUnderlierMustBeSecurity";
	String DEFINITION = "if exchangeTradedContractNearest exists then exchangeTradedContractNearest -> productIdentifier exists";
	
	ValidationResult<VolatilityReturnTerms> validate(RosettaPath path, VolatilityReturnTerms volatilityReturnTerms);
	
	class Default implements VolatilityReturnTermsUnderlierMustBeSecurity {
	
		@Override
		public ValidationResult<VolatilityReturnTerms> validate(RosettaPath path, VolatilityReturnTerms volatilityReturnTerms) {
			ComparisonResult result = executeDataRule(volatilityReturnTerms);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "VolatilityReturnTerms", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "VolatilityReturnTerms", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(VolatilityReturnTerms volatilityReturnTerms) {
			try {
				if (exists(MapperS.of(volatilityReturnTerms).<Observable>map("getExchangeTradedContractNearest", _volatilityReturnTerms -> _volatilityReturnTerms.getExchangeTradedContractNearest())).getOrDefault(false)) {
					return exists(MapperS.of(volatilityReturnTerms).<Observable>map("getExchangeTradedContractNearest", _volatilityReturnTerms -> _volatilityReturnTerms.getExchangeTradedContractNearest()).<FieldWithMetaProductIdentifier>mapC("getProductIdentifier", observable -> observable.getProductIdentifier()).<ProductIdentifier>map("getValue", _f->_f.getValue()));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements VolatilityReturnTermsUnderlierMustBeSecurity {
	
		@Override
		public ValidationResult<VolatilityReturnTerms> validate(RosettaPath path, VolatilityReturnTerms volatilityReturnTerms) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "VolatilityReturnTerms", path, DEFINITION);
		}
	}
}
