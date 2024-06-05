package cdm.event.common.meta;

import cdm.event.common.SettlementOrigin;
import cdm.event.common.validation.SettlementOriginTypeFormatValidator;
import cdm.event.common.validation.SettlementOriginValidator;
import cdm.event.common.validation.exists.SettlementOriginOnlyExistsValidator;
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
@RosettaMeta(model=SettlementOrigin.class)
public class SettlementOriginMeta implements RosettaMetaData<SettlementOrigin> {

	@Override
	public List<Validator<? super SettlementOrigin>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.event.common.validation.datarule.SettlementOriginOneOf0.class)
		);
	}
	
	@Override
	public List<Function<? super SettlementOrigin, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super SettlementOrigin> validator() {
		return new SettlementOriginValidator();
	}

	@Override
	public Validator<? super SettlementOrigin> typeFormatValidator() {
		return new SettlementOriginTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super SettlementOrigin, Set<String>> onlyExistsValidator() {
		return new SettlementOriginOnlyExistsValidator();
	}
}
