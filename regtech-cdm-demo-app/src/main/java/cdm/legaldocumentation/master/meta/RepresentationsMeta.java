package cdm.legaldocumentation.master.meta;

import cdm.legaldocumentation.master.Representations;
import cdm.legaldocumentation.master.validation.RepresentationsTypeFormatValidator;
import cdm.legaldocumentation.master.validation.RepresentationsValidator;
import cdm.legaldocumentation.master.validation.exists.RepresentationsOnlyExistsValidator;
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
@RosettaMeta(model=Representations.class)
public class RepresentationsMeta implements RosettaMetaData<Representations> {

	@Override
	public List<Validator<? super Representations>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super Representations, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super Representations> validator() {
		return new RepresentationsValidator();
	}

	@Override
	public Validator<? super Representations> typeFormatValidator() {
		return new RepresentationsTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super Representations, Set<String>> onlyExistsValidator() {
		return new RepresentationsOnlyExistsValidator();
	}
}
