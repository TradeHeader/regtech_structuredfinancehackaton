package cdm.event.common.meta;

import cdm.event.common.MarginCallBase;
import cdm.event.common.validation.MarginCallBaseTypeFormatValidator;
import cdm.event.common.validation.MarginCallBaseValidator;
import cdm.event.common.validation.exists.MarginCallBaseOnlyExistsValidator;
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
@RosettaMeta(model=MarginCallBase.class)
public class MarginCallBaseMeta implements RosettaMetaData<MarginCallBase> {

	@Override
	public List<Validator<? super MarginCallBase>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.event.common.validation.datarule.MarginCallBaseRegIMRoleIMOnly.class)
		);
	}
	
	@Override
	public List<Function<? super MarginCallBase, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super MarginCallBase> validator() {
		return new MarginCallBaseValidator();
	}

	@Override
	public Validator<? super MarginCallBase> typeFormatValidator() {
		return new MarginCallBaseTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super MarginCallBase, Set<String>> onlyExistsValidator() {
		return new MarginCallBaseOnlyExistsValidator();
	}
}
