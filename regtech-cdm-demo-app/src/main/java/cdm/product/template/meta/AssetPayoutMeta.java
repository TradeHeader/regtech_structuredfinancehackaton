package cdm.product.template.meta;

import cdm.product.template.AssetPayout;
import cdm.product.template.validation.AssetPayoutTypeFormatValidator;
import cdm.product.template.validation.AssetPayoutValidator;
import cdm.product.template.validation.exists.AssetPayoutOnlyExistsValidator;
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
@RosettaMeta(model=AssetPayout.class)
public class AssetPayoutMeta implements RosettaMetaData<AssetPayout> {

	@Override
	public List<Validator<? super AssetPayout>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.template.validation.datarule.AssetPayoutQuantity.class),
			factory.create(cdm.product.template.validation.datarule.AssetPayoutProductMustBeSecurity.class),
			factory.create(cdm.product.template.validation.datarule.AssetPayoutDividendTermsValidation.class)
		);
	}
	
	@Override
	public List<Function<? super AssetPayout, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super AssetPayout> validator() {
		return new AssetPayoutValidator();
	}

	@Override
	public Validator<? super AssetPayout> typeFormatValidator() {
		return new AssetPayoutTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super AssetPayout, Set<String>> onlyExistsValidator() {
		return new AssetPayoutOnlyExistsValidator();
	}
}
