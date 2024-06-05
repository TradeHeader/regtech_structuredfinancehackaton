package cdm.product.asset.validation.datarule;

import cdm.product.asset.ReferencePool;
import cdm.product.asset.ReferencePoolItem;
import cdm.product.template.ConstituentWeight;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.math.BigDecimal;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

/**
 * @version ${project.version}
 */
@RosettaDataRule("ReferencePoolFpML_cd_44_basketPercentage")
@ImplementedBy(ReferencePoolFpMLCd44BasketPercentage.Default.class)
public interface ReferencePoolFpMLCd44BasketPercentage extends Validator<ReferencePool> {
	
	String NAME = "ReferencePoolFpML_cd_44_basketPercentage";
	String DEFINITION = "if referencePoolItem -> constituentWeight -> basketPercentage exists then referencePoolItem -> constituentWeight -> openUnits is absent";
	
	ValidationResult<ReferencePool> validate(RosettaPath path, ReferencePool referencePool);
	
	class Default implements ReferencePoolFpMLCd44BasketPercentage {
	
		@Override
		public ValidationResult<ReferencePool> validate(RosettaPath path, ReferencePool referencePool) {
			ComparisonResult result = executeDataRule(referencePool);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ReferencePool", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "ReferencePool", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(ReferencePool referencePool) {
			try {
				if (exists(MapperS.of(referencePool).<ReferencePoolItem>mapC("getReferencePoolItem", _referencePool -> _referencePool.getReferencePoolItem()).<ConstituentWeight>map("getConstituentWeight", referencePoolItem -> referencePoolItem.getConstituentWeight()).<BigDecimal>map("getBasketPercentage", constituentWeight -> constituentWeight.getBasketPercentage())).getOrDefault(false)) {
					return notExists(MapperS.of(referencePool).<ReferencePoolItem>mapC("getReferencePoolItem", _referencePool -> _referencePool.getReferencePoolItem()).<ConstituentWeight>map("getConstituentWeight", referencePoolItem -> referencePoolItem.getConstituentWeight()).<BigDecimal>map("getOpenUnits", constituentWeight -> constituentWeight.getOpenUnits()));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements ReferencePoolFpMLCd44BasketPercentage {
	
		@Override
		public ValidationResult<ReferencePool> validate(RosettaPath path, ReferencePool referencePool) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ReferencePool", path, DEFINITION);
		}
	}
}
