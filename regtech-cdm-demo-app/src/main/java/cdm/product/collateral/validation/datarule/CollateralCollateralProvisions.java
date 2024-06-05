package cdm.product.collateral.validation.datarule;

import cdm.event.common.CollateralPortfolio;
import cdm.event.common.metafields.ReferenceWithMetaCollateralPortfolio;
import cdm.product.collateral.Collateral;
import cdm.product.collateral.CollateralProvisions;
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
@RosettaDataRule("CollateralCollateralProvisions")
@ImplementedBy(CollateralCollateralProvisions.Default.class)
public interface CollateralCollateralProvisions extends Validator<Collateral> {
	
	String NAME = "CollateralCollateralProvisions";
	String DEFINITION = "if collateralPortfolio exists then collateralProvisions exists";
	
	ValidationResult<Collateral> validate(RosettaPath path, Collateral collateral);
	
	class Default implements CollateralCollateralProvisions {
	
		@Override
		public ValidationResult<Collateral> validate(RosettaPath path, Collateral collateral) {
			ComparisonResult result = executeDataRule(collateral);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Collateral", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "Collateral", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(Collateral collateral) {
			try {
				if (exists(MapperS.of(collateral).<ReferenceWithMetaCollateralPortfolio>mapC("getCollateralPortfolio", _collateral -> _collateral.getCollateralPortfolio()).<CollateralPortfolio>map("getValue", _f->_f.getValue())).getOrDefault(false)) {
					return exists(MapperS.of(collateral).<CollateralProvisions>map("getCollateralProvisions", _collateral -> _collateral.getCollateralProvisions()));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements CollateralCollateralProvisions {
	
		@Override
		public ValidationResult<Collateral> validate(RosettaPath path, Collateral collateral) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Collateral", path, DEFINITION);
		}
	}
}
