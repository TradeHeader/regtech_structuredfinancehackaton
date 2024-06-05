package cdm.product.template.meta;

import cdm.product.template.OptionStyle;
import cdm.product.template.validation.OptionStyleTypeFormatValidator;
import cdm.product.template.validation.OptionStyleValidator;
import cdm.product.template.validation.exists.OptionStyleOnlyExistsValidator;
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
@RosettaMeta(model=OptionStyle.class)
public class OptionStyleMeta implements RosettaMetaData<OptionStyle> {

	@Override
	public List<Validator<? super OptionStyle>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.template.validation.datarule.OptionStyleOneOf0.class)
		);
	}
	
	@Override
	public List<Function<? super OptionStyle, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super OptionStyle> validator() {
		return new OptionStyleValidator();
	}

	@Override
	public Validator<? super OptionStyle> typeFormatValidator() {
		return new OptionStyleTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super OptionStyle, Set<String>> onlyExistsValidator() {
		return new OptionStyleOnlyExistsValidator();
	}
}
