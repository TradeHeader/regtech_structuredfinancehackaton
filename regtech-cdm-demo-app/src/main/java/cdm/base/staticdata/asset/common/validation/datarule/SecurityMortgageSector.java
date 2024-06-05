package cdm.base.staticdata.asset.common.validation.datarule;

import cdm.base.staticdata.asset.common.MortgageSectorEnum;
import cdm.base.staticdata.asset.common.Security;
import cdm.base.staticdata.asset.common.SecurityTypeEnum;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.CardinalityOperator;
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
@RosettaDataRule("SecurityMortgageSector")
@ImplementedBy(SecurityMortgageSector.Default.class)
public interface SecurityMortgageSector extends Validator<Security> {
	
	String NAME = "SecurityMortgageSector";
	String DEFINITION = "if securityType <> SecurityTypeEnum -> Mortgage then mortgageSector is absent";
	
	ValidationResult<Security> validate(RosettaPath path, Security security);
	
	class Default implements SecurityMortgageSector {
	
		@Override
		public ValidationResult<Security> validate(RosettaPath path, Security security) {
			ComparisonResult result = executeDataRule(security);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Security", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "Security", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(Security security) {
			try {
				if (notEqual(MapperS.of(security).<SecurityTypeEnum>map("getSecurityType", _security -> _security.getSecurityType()), MapperS.of(SecurityTypeEnum.MORTGAGE), CardinalityOperator.Any).getOrDefault(false)) {
					return notExists(MapperS.of(security).<MortgageSectorEnum>map("getMortgageSector", _security -> _security.getMortgageSector()));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements SecurityMortgageSector {
	
		@Override
		public ValidationResult<Security> validate(RosettaPath path, Security security) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Security", path, DEFINITION);
		}
	}
}
