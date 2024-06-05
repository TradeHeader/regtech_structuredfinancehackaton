package cdm.product.asset.meta;

import cdm.product.asset.AssetDeliveryPeriods;
import cdm.product.asset.validation.AssetDeliveryPeriodsTypeFormatValidator;
import cdm.product.asset.validation.AssetDeliveryPeriodsValidator;
import cdm.product.asset.validation.exists.AssetDeliveryPeriodsOnlyExistsValidator;
import com.rosetta.model.lib.annotations.RosettaMeta;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.qualify.QualifyFunctionFactory;
import com.rosetta.model.lib.qualify.QualifyResult;
import com.rosetta.model.lib.validation.Validator;
import com.rosetta.model.lib.validation.ValidatorFactory;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Function;


/**
 * @version ${project.version}
 */
@RosettaMeta(model=AssetDeliveryPeriods.class)
public class AssetDeliveryPeriodsMeta implements RosettaMetaData<AssetDeliveryPeriods> {

	@Override
	public List<Validator<? super AssetDeliveryPeriods>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super AssetDeliveryPeriods, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super AssetDeliveryPeriods> validator() {
		return new AssetDeliveryPeriodsValidator();
	}

	@Override
	public Validator<? super AssetDeliveryPeriods> typeFormatValidator() {
		return new AssetDeliveryPeriodsTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super AssetDeliveryPeriods, Set<String>> onlyExistsValidator() {
		return new AssetDeliveryPeriodsOnlyExistsValidator();
	}
}
