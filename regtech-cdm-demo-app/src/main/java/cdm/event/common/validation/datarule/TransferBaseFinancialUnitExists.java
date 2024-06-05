package cdm.event.common.validation.datarule;

import cdm.base.math.FinancialUnitEnum;
import cdm.base.math.NonNegativeQuantity;
import cdm.base.math.UnitType;
import cdm.event.common.TransferBase;
import cdm.observable.asset.Observable;
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
@RosettaDataRule("TransferBaseFinancialUnitExists")
@ImplementedBy(TransferBaseFinancialUnitExists.Default.class)
public interface TransferBaseFinancialUnitExists extends Validator<TransferBase> {
	
	String NAME = "TransferBaseFinancialUnitExists";
	String DEFINITION = "if observable exists then quantity -> unit -> financialUnit exists";
	
	ValidationResult<TransferBase> validate(RosettaPath path, TransferBase transferBase);
	
	class Default implements TransferBaseFinancialUnitExists {
	
		@Override
		public ValidationResult<TransferBase> validate(RosettaPath path, TransferBase transferBase) {
			ComparisonResult result = executeDataRule(transferBase);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "TransferBase", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "TransferBase", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(TransferBase transferBase) {
			try {
				if (exists(MapperS.of(transferBase).<Observable>map("getObservable", _transferBase -> _transferBase.getObservable())).getOrDefault(false)) {
					return exists(MapperS.of(transferBase).<NonNegativeQuantity>map("getQuantity", _transferBase -> _transferBase.getQuantity()).<UnitType>map("getUnit", measureBase -> measureBase.getUnit()).<FinancialUnitEnum>map("getFinancialUnit", unitType -> unitType.getFinancialUnit()));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements TransferBaseFinancialUnitExists {
	
		@Override
		public ValidationResult<TransferBase> validate(RosettaPath path, TransferBase transferBase) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "TransferBase", path, DEFINITION);
		}
	}
}
