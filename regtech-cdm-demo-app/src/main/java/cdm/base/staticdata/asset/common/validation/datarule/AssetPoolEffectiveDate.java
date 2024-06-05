package cdm.base.staticdata.asset.common.validation.datarule;

import cdm.base.staticdata.asset.common.AssetPool;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.records.Date;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

/**
 * @version ${project.version}
 */
@RosettaDataRule("AssetPoolEffectiveDate")
@ImplementedBy(AssetPoolEffectiveDate.Default.class)
public interface AssetPoolEffectiveDate extends Validator<AssetPool> {
	
	String NAME = "AssetPoolEffectiveDate";
	String DEFINITION = "if version is absent then effectiveDate is absent";
	
	ValidationResult<AssetPool> validate(RosettaPath path, AssetPool assetPool);
	
	class Default implements AssetPoolEffectiveDate {
	
		@Override
		public ValidationResult<AssetPool> validate(RosettaPath path, AssetPool assetPool) {
			ComparisonResult result = executeDataRule(assetPool);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "AssetPool", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "AssetPool", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(AssetPool assetPool) {
			try {
				if (notExists(MapperS.of(assetPool).<String>map("getVersion", _assetPool -> _assetPool.getVersion())).getOrDefault(false)) {
					return notExists(MapperS.of(assetPool).<Date>map("getEffectiveDate", _assetPool -> _assetPool.getEffectiveDate()));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements AssetPoolEffectiveDate {
	
		@Override
		public ValidationResult<AssetPool> validate(RosettaPath path, AssetPool assetPool) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "AssetPool", path, DEFINITION);
		}
	}
}
