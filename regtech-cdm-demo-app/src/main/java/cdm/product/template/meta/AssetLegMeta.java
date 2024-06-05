package cdm.product.template.meta;

import cdm.product.template.AssetLeg;
import cdm.product.template.validation.AssetLegTypeFormatValidator;
import cdm.product.template.validation.AssetLegValidator;
import cdm.product.template.validation.exists.AssetLegOnlyExistsValidator;
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
@RosettaMeta(model=AssetLeg.class)
public class AssetLegMeta implements RosettaMetaData<AssetLeg> {

	@Override
	public List<Validator<? super AssetLeg>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super AssetLeg, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super AssetLeg> validator() {
		return new AssetLegValidator();
	}

	@Override
	public Validator<? super AssetLeg> typeFormatValidator() {
		return new AssetLegTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super AssetLeg, Set<String>> onlyExistsValidator() {
		return new AssetLegOnlyExistsValidator();
	}
}
