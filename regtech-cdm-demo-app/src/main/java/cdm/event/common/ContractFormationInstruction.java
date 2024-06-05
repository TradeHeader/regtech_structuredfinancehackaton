package cdm.event.common;

import cdm.event.common.ContractFormationInstruction;
import cdm.event.common.ContractFormationInstruction.ContractFormationInstructionBuilder;
import cdm.event.common.ContractFormationInstruction.ContractFormationInstructionBuilderImpl;
import cdm.event.common.ContractFormationInstruction.ContractFormationInstructionImpl;
import cdm.event.common.meta.ContractFormationInstructionMeta;
import cdm.legaldocumentation.common.LegalAgreement;
import com.google.common.collect.ImmutableList;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.util.ListEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * Specifies instructions to create a fully formed contract, with optional legal agreements.
 * @version ${project.version}
 */
@RosettaDataType(value="ContractFormationInstruction", builder=ContractFormationInstruction.ContractFormationInstructionBuilderImpl.class, version="${project.version}")
public interface ContractFormationInstruction extends RosettaModelObject {

	ContractFormationInstructionMeta metaData = new ContractFormationInstructionMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Optional legal agreements associated to the contract being formed, for instance a master agreement.
	 */
	List<? extends LegalAgreement> getLegalAgreement();

	/*********************** Build Methods  ***********************/
	ContractFormationInstruction build();
	
	ContractFormationInstruction.ContractFormationInstructionBuilder toBuilder();
	
	static ContractFormationInstruction.ContractFormationInstructionBuilder builder() {
		return new ContractFormationInstruction.ContractFormationInstructionBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ContractFormationInstruction> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ContractFormationInstruction> getType() {
		return ContractFormationInstruction.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("legalAgreement"), processor, LegalAgreement.class, getLegalAgreement());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ContractFormationInstructionBuilder extends ContractFormationInstruction, RosettaModelObjectBuilder {
		LegalAgreement.LegalAgreementBuilder getOrCreateLegalAgreement(int _index);
		List<? extends LegalAgreement.LegalAgreementBuilder> getLegalAgreement();
		ContractFormationInstruction.ContractFormationInstructionBuilder addLegalAgreement(LegalAgreement legalAgreement0);
		ContractFormationInstruction.ContractFormationInstructionBuilder addLegalAgreement(LegalAgreement legalAgreement1, int _idx);
		ContractFormationInstruction.ContractFormationInstructionBuilder addLegalAgreement(List<? extends LegalAgreement> legalAgreement2);
		ContractFormationInstruction.ContractFormationInstructionBuilder setLegalAgreement(List<? extends LegalAgreement> legalAgreement3);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("legalAgreement"), processor, LegalAgreement.LegalAgreementBuilder.class, getLegalAgreement());
		}
		

		ContractFormationInstruction.ContractFormationInstructionBuilder prune();
	}

	/*********************** Immutable Implementation of ContractFormationInstruction  ***********************/
	class ContractFormationInstructionImpl implements ContractFormationInstruction {
		private final List<? extends LegalAgreement> legalAgreement;
		
		protected ContractFormationInstructionImpl(ContractFormationInstruction.ContractFormationInstructionBuilder builder) {
			this.legalAgreement = ofNullable(builder.getLegalAgreement()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
		}
		
		@Override
		@RosettaAttribute("legalAgreement")
		public List<? extends LegalAgreement> getLegalAgreement() {
			return legalAgreement;
		}
		
		@Override
		public ContractFormationInstruction build() {
			return this;
		}
		
		@Override
		public ContractFormationInstruction.ContractFormationInstructionBuilder toBuilder() {
			ContractFormationInstruction.ContractFormationInstructionBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ContractFormationInstruction.ContractFormationInstructionBuilder builder) {
			ofNullable(getLegalAgreement()).ifPresent(builder::setLegalAgreement);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ContractFormationInstruction _that = getType().cast(o);
		
			if (!ListEquals.listEquals(legalAgreement, _that.getLegalAgreement())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (legalAgreement != null ? legalAgreement.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ContractFormationInstruction {" +
				"legalAgreement=" + this.legalAgreement +
			'}';
		}
	}

	/*********************** Builder Implementation of ContractFormationInstruction  ***********************/
	class ContractFormationInstructionBuilderImpl implements ContractFormationInstruction.ContractFormationInstructionBuilder {
	
		protected List<LegalAgreement.LegalAgreementBuilder> legalAgreement = new ArrayList<>();
	
		public ContractFormationInstructionBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("legalAgreement")
		public List<? extends LegalAgreement.LegalAgreementBuilder> getLegalAgreement() {
			return legalAgreement;
		}
		
		public LegalAgreement.LegalAgreementBuilder getOrCreateLegalAgreement(int _index) {
		
			if (legalAgreement==null) {
				this.legalAgreement = new ArrayList<>();
			}
			LegalAgreement.LegalAgreementBuilder result;
			return getIndex(legalAgreement, _index, () -> {
						LegalAgreement.LegalAgreementBuilder newLegalAgreement = LegalAgreement.builder();
						return newLegalAgreement;
					});
		}
		
	
		@Override
		public ContractFormationInstruction.ContractFormationInstructionBuilder addLegalAgreement(LegalAgreement legalAgreement) {
			if (legalAgreement!=null) this.legalAgreement.add(legalAgreement.toBuilder());
			return this;
		}
		
		@Override
		public ContractFormationInstruction.ContractFormationInstructionBuilder addLegalAgreement(LegalAgreement legalAgreement, int _idx) {
			getIndex(this.legalAgreement, _idx, () -> legalAgreement.toBuilder());
			return this;
		}
		@Override 
		public ContractFormationInstruction.ContractFormationInstructionBuilder addLegalAgreement(List<? extends LegalAgreement> legalAgreements) {
			if (legalAgreements != null) {
				for (LegalAgreement toAdd : legalAgreements) {
					this.legalAgreement.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("legalAgreement")
		public ContractFormationInstruction.ContractFormationInstructionBuilder setLegalAgreement(List<? extends LegalAgreement> legalAgreements) {
			if (legalAgreements == null)  {
				this.legalAgreement = new ArrayList<>();
			}
			else {
				this.legalAgreement = legalAgreements.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		
		@Override
		public ContractFormationInstruction build() {
			return new ContractFormationInstruction.ContractFormationInstructionImpl(this);
		}
		
		@Override
		public ContractFormationInstruction.ContractFormationInstructionBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ContractFormationInstruction.ContractFormationInstructionBuilder prune() {
			legalAgreement = legalAgreement.stream().filter(b->b!=null).<LegalAgreement.LegalAgreementBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getLegalAgreement()!=null && getLegalAgreement().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ContractFormationInstruction.ContractFormationInstructionBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ContractFormationInstruction.ContractFormationInstructionBuilder o = (ContractFormationInstruction.ContractFormationInstructionBuilder) other;
			
			merger.mergeRosetta(getLegalAgreement(), o.getLegalAgreement(), this::getOrCreateLegalAgreement);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ContractFormationInstruction _that = getType().cast(o);
		
			if (!ListEquals.listEquals(legalAgreement, _that.getLegalAgreement())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (legalAgreement != null ? legalAgreement.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ContractFormationInstructionBuilder {" +
				"legalAgreement=" + this.legalAgreement +
			'}';
		}
	}
}
