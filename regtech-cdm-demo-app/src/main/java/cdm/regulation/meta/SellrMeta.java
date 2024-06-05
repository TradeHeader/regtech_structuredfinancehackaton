package cdm.regulation.meta;

import cdm.regulation.Sellr;
import cdm.regulation.validation.SellrTypeFormatValidator;
import cdm.regulation.validation.SellrValidator;
import cdm.regulation.validation.exists.SellrOnlyExistsValidator;
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
@RosettaMeta(model=Sellr.class)
public class SellrMeta implements RosettaMetaData<Sellr> {

	@Override
	public List<Validator<? super Sellr>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super Sellr, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super Sellr> validator() {
		return new SellrValidator();
	}

	@Override
	public Validator<? super Sellr> typeFormatValidator() {
		return new SellrTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super Sellr, Set<String>> onlyExistsValidator() {
		return new SellrOnlyExistsValidator();
	}
}
