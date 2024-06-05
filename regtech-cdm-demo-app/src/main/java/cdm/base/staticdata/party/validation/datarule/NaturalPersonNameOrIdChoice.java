package cdm.base.staticdata.party.validation.datarule;

import cdm.base.staticdata.party.NaturalPerson;
import cdm.base.staticdata.party.PersonIdentifier;
import cdm.base.staticdata.party.metafields.FieldWithMetaPersonIdentifier;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

/**
 * @version ${project.version}
 */
@RosettaDataRule("NaturalPersonNameOrIdChoice")
@ImplementedBy(NaturalPersonNameOrIdChoice.Default.class)
public interface NaturalPersonNameOrIdChoice extends Validator<NaturalPerson> {
	
	String NAME = "NaturalPersonNameOrIdChoice";
	String DEFINITION = "(firstName exists and surname exists) or personId exists";
	
	ValidationResult<NaturalPerson> validate(RosettaPath path, NaturalPerson naturalPerson);
	
	class Default implements NaturalPersonNameOrIdChoice {
	
		@Override
		public ValidationResult<NaturalPerson> validate(RosettaPath path, NaturalPerson naturalPerson) {
			ComparisonResult result = executeDataRule(naturalPerson);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "NaturalPerson", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "NaturalPerson", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(NaturalPerson naturalPerson) {
			try {
				return exists(MapperS.of(naturalPerson).<String>map("getFirstName", _naturalPerson -> _naturalPerson.getFirstName())).and(exists(MapperS.of(naturalPerson).<String>map("getSurname", _naturalPerson -> _naturalPerson.getSurname()))).or(exists(MapperS.of(naturalPerson).<FieldWithMetaPersonIdentifier>mapC("getPersonId", _naturalPerson -> _naturalPerson.getPersonId()).<PersonIdentifier>map("getValue", _f->_f.getValue())));
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements NaturalPersonNameOrIdChoice {
	
		@Override
		public ValidationResult<NaturalPerson> validate(RosettaPath path, NaturalPerson naturalPerson) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "NaturalPerson", path, DEFINITION);
		}
	}
}
