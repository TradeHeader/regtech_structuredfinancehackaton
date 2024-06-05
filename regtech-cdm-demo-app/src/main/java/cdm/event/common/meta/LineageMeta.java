package cdm.event.common.meta;

import cdm.event.common.Lineage;
import cdm.event.common.validation.LineageTypeFormatValidator;
import cdm.event.common.validation.LineageValidator;
import cdm.event.common.validation.exists.LineageOnlyExistsValidator;
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
@RosettaMeta(model=Lineage.class)
public class LineageMeta implements RosettaMetaData<Lineage> {

	@Override
	public List<Validator<? super Lineage>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super Lineage, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super Lineage> validator() {
		return new LineageValidator();
	}

	@Override
	public Validator<? super Lineage> typeFormatValidator() {
		return new LineageTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super Lineage, Set<String>> onlyExistsValidator() {
		return new LineageOnlyExistsValidator();
	}
}
