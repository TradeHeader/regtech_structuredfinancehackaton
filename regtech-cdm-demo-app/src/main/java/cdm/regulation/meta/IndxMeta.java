package cdm.regulation.meta;

import cdm.regulation.Indx;
import cdm.regulation.validation.IndxTypeFormatValidator;
import cdm.regulation.validation.IndxValidator;
import cdm.regulation.validation.exists.IndxOnlyExistsValidator;
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
@RosettaMeta(model=Indx.class)
public class IndxMeta implements RosettaMetaData<Indx> {

	@Override
	public List<Validator<? super Indx>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super Indx, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super Indx> validator() {
		return new IndxValidator();
	}

	@Override
	public Validator<? super Indx> typeFormatValidator() {
		return new IndxTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super Indx, Set<String>> onlyExistsValidator() {
		return new IndxOnlyExistsValidator();
	}
}
