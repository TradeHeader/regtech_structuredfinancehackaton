package cdm.product.template.meta;

import cdm.product.template.Quanto;
import cdm.product.template.validation.QuantoTypeFormatValidator;
import cdm.product.template.validation.QuantoValidator;
import cdm.product.template.validation.exists.QuantoOnlyExistsValidator;
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
@RosettaMeta(model=Quanto.class)
public class QuantoMeta implements RosettaMetaData<Quanto> {

	@Override
	public List<Validator<? super Quanto>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super Quanto, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super Quanto> validator() {
		return new QuantoValidator();
	}

	@Override
	public Validator<? super Quanto> typeFormatValidator() {
		return new QuantoTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super Quanto, Set<String>> onlyExistsValidator() {
		return new QuantoOnlyExistsValidator();
	}
}
