package cdm.event.common.meta;

import cdm.event.common.TradeIdentifier;
import cdm.event.common.validation.TradeIdentifierTypeFormatValidator;
import cdm.event.common.validation.TradeIdentifierValidator;
import cdm.event.common.validation.exists.TradeIdentifierOnlyExistsValidator;
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
@RosettaMeta(model=TradeIdentifier.class)
public class TradeIdentifierMeta implements RosettaMetaData<TradeIdentifier> {

	@Override
	public List<Validator<? super TradeIdentifier>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.base.staticdata.identifier.validation.datarule.IdentifierIssuerChoice.class)
		);
	}
	
	@Override
	public List<Function<? super TradeIdentifier, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super TradeIdentifier> validator() {
		return new TradeIdentifierValidator();
	}

	@Override
	public Validator<? super TradeIdentifier> typeFormatValidator() {
		return new TradeIdentifierTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super TradeIdentifier, Set<String>> onlyExistsValidator() {
		return new TradeIdentifierOnlyExistsValidator();
	}
}
