package cdm.event.common.meta;

import cdm.event.common.IndexTransitionInstruction;
import cdm.event.common.validation.IndexTransitionInstructionTypeFormatValidator;
import cdm.event.common.validation.IndexTransitionInstructionValidator;
import cdm.event.common.validation.exists.IndexTransitionInstructionOnlyExistsValidator;
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
@RosettaMeta(model=IndexTransitionInstruction.class)
public class IndexTransitionInstructionMeta implements RosettaMetaData<IndexTransitionInstruction> {

	@Override
	public List<Validator<? super IndexTransitionInstruction>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.event.common.validation.datarule.IndexTransitionInstructionPriceQuantity.class)
		);
	}
	
	@Override
	public List<Function<? super IndexTransitionInstruction, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super IndexTransitionInstruction> validator() {
		return new IndexTransitionInstructionValidator();
	}

	@Override
	public Validator<? super IndexTransitionInstruction> typeFormatValidator() {
		return new IndexTransitionInstructionTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super IndexTransitionInstruction, Set<String>> onlyExistsValidator() {
		return new IndexTransitionInstructionOnlyExistsValidator();
	}
}
