package cdm.observable.asset.validation.datarule;

import cdm.base.math.UnitType;
import cdm.observable.asset.Money;
import cdm.observable.asset.ValuationMethod;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import com.rosetta.model.metafields.FieldWithMetaString;
import java.math.BigDecimal;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

/**
 * @version ${project.version}
 */
@RosettaDataRule("ValuationMethodFpML_cd_37")
@ImplementedBy(ValuationMethodFpMLCd37.Default.class)
public interface ValuationMethodFpMLCd37 extends Validator<ValuationMethod> {
	
	String NAME = "ValuationMethodFpML_cd_37";
	String DEFINITION = "if (quotationAmount exists and minimumQuotationAmount exists) and quotationAmount -> unit -> currency = minimumQuotationAmount -> unit -> currency then quotationAmount -> value > minimumQuotationAmount -> value";
	
	ValidationResult<ValuationMethod> validate(RosettaPath path, ValuationMethod valuationMethod);
	
	class Default implements ValuationMethodFpMLCd37 {
	
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
				if (exists(MapperS.of(valuationMethod).<Money>map("getQuotationAmount", _valuationMethod -> _valuationMethod.getQuotationAmount())).and(exists(MapperS.of(valuationMethod).<Money>map("getMinimumQuotationAmount", _valuationMethod -> _valuationMethod.getMinimumQuotationAmount()))).and(areEqual(MapperS.of(valuationMethod).<Money>map("getQuotationAmount", _valuationMethod -> _valuationMethod.getQuotationAmount()).<UnitType>map("getUnit", measureBase -> measureBase.getUnit()).<FieldWithMetaString>map("getCurrency", unitType -> unitType.getCurrency()).<String>map("getValue", _f->_f.getValue()), MapperS.of(valuationMethod).<Money>map("getMinimumQuotationAmount", _valuationMethod -> _valuationMethod.getMinimumQuotationAmount()).<UnitType>map("getUnit", measureBase -> measureBase.getUnit()).<FieldWithMetaString>map("getCurrency", unitType -> unitType.getCurrency()).<String>map("getValue", _f->_f.getValue()), CardinalityOperator.All)).getOrDefault(false)) {
					return greaterThan(MapperS.of(valuationMethod).<Money>map("getQuotationAmount", _valuationMethod -> _valuationMethod.getQuotationAmount()).<BigDecimal>map("getValue", measureBase -> measureBase.getValue()), MapperS.of(valuationMethod).<Money>map("getMinimumQuotationAmount", _valuationMethod -> _valuationMethod.getMinimumQuotationAmount()).<BigDecimal>map("getValue", measureBase -> measureBase.getValue()), CardinalityOperator.All);
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements ValuationMethodFpMLCd37 {
	
		@Override
		public ValidationResult<ValuationMethod> validate(RosettaPath path, ValuationMethod valuationMethod) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ValuationMethod", path, DEFINITION);
		}
	}
}
