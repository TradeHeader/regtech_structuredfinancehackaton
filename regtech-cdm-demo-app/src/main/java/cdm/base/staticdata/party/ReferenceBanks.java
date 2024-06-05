package cdm.base.staticdata.party;

import cdm.base.staticdata.party.ReferenceBank;
import cdm.base.staticdata.party.ReferenceBanks;
import cdm.base.staticdata.party.ReferenceBanks.ReferenceBanksBuilder;
import cdm.base.staticdata.party.ReferenceBanks.ReferenceBanksBuilderImpl;
import cdm.base.staticdata.party.ReferenceBanks.ReferenceBanksImpl;
import cdm.base.staticdata.party.meta.ReferenceBanksMeta;
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
 * A class defining the list of reference institutions polled for relevant rates or prices when determining the cash settlement amount for a product where cash settlement is applicable.
 * @version ${project.version}
 */
@RosettaDataType(value="ReferenceBanks", builder=ReferenceBanks.ReferenceBanksBuilderImpl.class, version="${project.version}")
public interface ReferenceBanks extends RosettaModelObject {

	ReferenceBanksMeta metaData = new ReferenceBanksMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * An institution (party) identified by means of a coding scheme and an optional name.
	 */
	List<? extends ReferenceBank> getReferenceBank();

	/*********************** Build Methods  ***********************/
	ReferenceBanks build();
	
	ReferenceBanks.ReferenceBanksBuilder toBuilder();
	
	static ReferenceBanks.ReferenceBanksBuilder builder() {
		return new ReferenceBanks.ReferenceBanksBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ReferenceBanks> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ReferenceBanks> getType() {
		return ReferenceBanks.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("referenceBank"), processor, ReferenceBank.class, getReferenceBank());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ReferenceBanksBuilder extends ReferenceBanks, RosettaModelObjectBuilder {
		ReferenceBank.ReferenceBankBuilder getOrCreateReferenceBank(int _index);
		List<? extends ReferenceBank.ReferenceBankBuilder> getReferenceBank();
		ReferenceBanks.ReferenceBanksBuilder addReferenceBank(ReferenceBank referenceBank0);
		ReferenceBanks.ReferenceBanksBuilder addReferenceBank(ReferenceBank referenceBank1, int _idx);
		ReferenceBanks.ReferenceBanksBuilder addReferenceBank(List<? extends ReferenceBank> referenceBank2);
		ReferenceBanks.ReferenceBanksBuilder setReferenceBank(List<? extends ReferenceBank> referenceBank3);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("referenceBank"), processor, ReferenceBank.ReferenceBankBuilder.class, getReferenceBank());
		}
		

		ReferenceBanks.ReferenceBanksBuilder prune();
	}

	/*********************** Immutable Implementation of ReferenceBanks  ***********************/
	class ReferenceBanksImpl implements ReferenceBanks {
		private final List<? extends ReferenceBank> referenceBank;
		
		protected ReferenceBanksImpl(ReferenceBanks.ReferenceBanksBuilder builder) {
			this.referenceBank = ofNullable(builder.getReferenceBank()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
		}
		
		@Override
		@RosettaAttribute("referenceBank")
		public List<? extends ReferenceBank> getReferenceBank() {
			return referenceBank;
		}
		
		@Override
		public ReferenceBanks build() {
			return this;
		}
		
		@Override
		public ReferenceBanks.ReferenceBanksBuilder toBuilder() {
			ReferenceBanks.ReferenceBanksBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ReferenceBanks.ReferenceBanksBuilder builder) {
			ofNullable(getReferenceBank()).ifPresent(builder::setReferenceBank);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ReferenceBanks _that = getType().cast(o);
		
			if (!ListEquals.listEquals(referenceBank, _that.getReferenceBank())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (referenceBank != null ? referenceBank.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ReferenceBanks {" +
				"referenceBank=" + this.referenceBank +
			'}';
		}
	}

	/*********************** Builder Implementation of ReferenceBanks  ***********************/
	class ReferenceBanksBuilderImpl implements ReferenceBanks.ReferenceBanksBuilder {
	
		protected List<ReferenceBank.ReferenceBankBuilder> referenceBank = new ArrayList<>();
	
		public ReferenceBanksBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("referenceBank")
		public List<? extends ReferenceBank.ReferenceBankBuilder> getReferenceBank() {
			return referenceBank;
		}
		
		public ReferenceBank.ReferenceBankBuilder getOrCreateReferenceBank(int _index) {
		
			if (referenceBank==null) {
				this.referenceBank = new ArrayList<>();
			}
			ReferenceBank.ReferenceBankBuilder result;
			return getIndex(referenceBank, _index, () -> {
						ReferenceBank.ReferenceBankBuilder newReferenceBank = ReferenceBank.builder();
						return newReferenceBank;
					});
		}
		
	
		@Override
		public ReferenceBanks.ReferenceBanksBuilder addReferenceBank(ReferenceBank referenceBank) {
			if (referenceBank!=null) this.referenceBank.add(referenceBank.toBuilder());
			return this;
		}
		
		@Override
		public ReferenceBanks.ReferenceBanksBuilder addReferenceBank(ReferenceBank referenceBank, int _idx) {
			getIndex(this.referenceBank, _idx, () -> referenceBank.toBuilder());
			return this;
		}
		@Override 
		public ReferenceBanks.ReferenceBanksBuilder addReferenceBank(List<? extends ReferenceBank> referenceBanks) {
			if (referenceBanks != null) {
				for (ReferenceBank toAdd : referenceBanks) {
					this.referenceBank.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("referenceBank")
		public ReferenceBanks.ReferenceBanksBuilder setReferenceBank(List<? extends ReferenceBank> referenceBanks) {
			if (referenceBanks == null)  {
				this.referenceBank = new ArrayList<>();
			}
			else {
				this.referenceBank = referenceBanks.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		
		@Override
		public ReferenceBanks build() {
			return new ReferenceBanks.ReferenceBanksImpl(this);
		}
		
		@Override
		public ReferenceBanks.ReferenceBanksBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ReferenceBanks.ReferenceBanksBuilder prune() {
			referenceBank = referenceBank.stream().filter(b->b!=null).<ReferenceBank.ReferenceBankBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getReferenceBank()!=null && getReferenceBank().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ReferenceBanks.ReferenceBanksBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ReferenceBanks.ReferenceBanksBuilder o = (ReferenceBanks.ReferenceBanksBuilder) other;
			
			merger.mergeRosetta(getReferenceBank(), o.getReferenceBank(), this::getOrCreateReferenceBank);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ReferenceBanks _that = getType().cast(o);
		
			if (!ListEquals.listEquals(referenceBank, _that.getReferenceBank())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (referenceBank != null ? referenceBank.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ReferenceBanksBuilder {" +
				"referenceBank=" + this.referenceBank +
			'}';
		}
	}
}
