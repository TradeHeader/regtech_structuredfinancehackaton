package cdm.legaldocumentation.master;

import cdm.legaldocumentation.master.MasterAgreementClause;
import cdm.legaldocumentation.master.MasterAgreementSchedule;
import cdm.legaldocumentation.master.MasterAgreementSchedule.MasterAgreementScheduleBuilder;
import cdm.legaldocumentation.master.MasterAgreementSchedule.MasterAgreementScheduleBuilderImpl;
import cdm.legaldocumentation.master.MasterAgreementSchedule.MasterAgreementScheduleImpl;
import cdm.legaldocumentation.master.meta.MasterAgreementScheduleMeta;
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
 * The set of elections which specify a Master Agreement.
 * @version ${project.version}
 */
@RosettaDataType(value="MasterAgreementSchedule", builder=MasterAgreementSchedule.MasterAgreementScheduleBuilderImpl.class, version="${project.version}")
public interface MasterAgreementSchedule extends RosettaModelObject {

	MasterAgreementScheduleMeta metaData = new MasterAgreementScheduleMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Clauses that have had elections made against them in this Master Agreement. There must be at least one clause defined in the agreement.
	 */
	List<? extends MasterAgreementClause> getClause();

	/*********************** Build Methods  ***********************/
	MasterAgreementSchedule build();
	
	MasterAgreementSchedule.MasterAgreementScheduleBuilder toBuilder();
	
	static MasterAgreementSchedule.MasterAgreementScheduleBuilder builder() {
		return new MasterAgreementSchedule.MasterAgreementScheduleBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends MasterAgreementSchedule> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends MasterAgreementSchedule> getType() {
		return MasterAgreementSchedule.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("clause"), processor, MasterAgreementClause.class, getClause());
	}
	

	/*********************** Builder Interface  ***********************/
	interface MasterAgreementScheduleBuilder extends MasterAgreementSchedule, RosettaModelObjectBuilder {
		MasterAgreementClause.MasterAgreementClauseBuilder getOrCreateClause(int _index);
		List<? extends MasterAgreementClause.MasterAgreementClauseBuilder> getClause();
		MasterAgreementSchedule.MasterAgreementScheduleBuilder addClause(MasterAgreementClause clause0);
		MasterAgreementSchedule.MasterAgreementScheduleBuilder addClause(MasterAgreementClause clause1, int _idx);
		MasterAgreementSchedule.MasterAgreementScheduleBuilder addClause(List<? extends MasterAgreementClause> clause2);
		MasterAgreementSchedule.MasterAgreementScheduleBuilder setClause(List<? extends MasterAgreementClause> clause3);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("clause"), processor, MasterAgreementClause.MasterAgreementClauseBuilder.class, getClause());
		}
		

		MasterAgreementSchedule.MasterAgreementScheduleBuilder prune();
	}

	/*********************** Immutable Implementation of MasterAgreementSchedule  ***********************/
	class MasterAgreementScheduleImpl implements MasterAgreementSchedule {
		private final List<? extends MasterAgreementClause> clause;
		
		protected MasterAgreementScheduleImpl(MasterAgreementSchedule.MasterAgreementScheduleBuilder builder) {
			this.clause = ofNullable(builder.getClause()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
		}
		
		@Override
		@RosettaAttribute("clause")
		public List<? extends MasterAgreementClause> getClause() {
			return clause;
		}
		
		@Override
		public MasterAgreementSchedule build() {
			return this;
		}
		
		@Override
		public MasterAgreementSchedule.MasterAgreementScheduleBuilder toBuilder() {
			MasterAgreementSchedule.MasterAgreementScheduleBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(MasterAgreementSchedule.MasterAgreementScheduleBuilder builder) {
			ofNullable(getClause()).ifPresent(builder::setClause);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			MasterAgreementSchedule _that = getType().cast(o);
		
			if (!ListEquals.listEquals(clause, _that.getClause())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (clause != null ? clause.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "MasterAgreementSchedule {" +
				"clause=" + this.clause +
			'}';
		}
	}

	/*********************** Builder Implementation of MasterAgreementSchedule  ***********************/
	class MasterAgreementScheduleBuilderImpl implements MasterAgreementSchedule.MasterAgreementScheduleBuilder {
	
		protected List<MasterAgreementClause.MasterAgreementClauseBuilder> clause = new ArrayList<>();
	
		public MasterAgreementScheduleBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("clause")
		public List<? extends MasterAgreementClause.MasterAgreementClauseBuilder> getClause() {
			return clause;
		}
		
		public MasterAgreementClause.MasterAgreementClauseBuilder getOrCreateClause(int _index) {
		
			if (clause==null) {
				this.clause = new ArrayList<>();
			}
			MasterAgreementClause.MasterAgreementClauseBuilder result;
			return getIndex(clause, _index, () -> {
						MasterAgreementClause.MasterAgreementClauseBuilder newClause = MasterAgreementClause.builder();
						return newClause;
					});
		}
		
	
		@Override
		public MasterAgreementSchedule.MasterAgreementScheduleBuilder addClause(MasterAgreementClause clause) {
			if (clause!=null) this.clause.add(clause.toBuilder());
			return this;
		}
		
		@Override
		public MasterAgreementSchedule.MasterAgreementScheduleBuilder addClause(MasterAgreementClause clause, int _idx) {
			getIndex(this.clause, _idx, () -> clause.toBuilder());
			return this;
		}
		@Override 
		public MasterAgreementSchedule.MasterAgreementScheduleBuilder addClause(List<? extends MasterAgreementClause> clauses) {
			if (clauses != null) {
				for (MasterAgreementClause toAdd : clauses) {
					this.clause.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("clause")
		public MasterAgreementSchedule.MasterAgreementScheduleBuilder setClause(List<? extends MasterAgreementClause> clauses) {
			if (clauses == null)  {
				this.clause = new ArrayList<>();
			}
			else {
				this.clause = clauses.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		
		@Override
		public MasterAgreementSchedule build() {
			return new MasterAgreementSchedule.MasterAgreementScheduleImpl(this);
		}
		
		@Override
		public MasterAgreementSchedule.MasterAgreementScheduleBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public MasterAgreementSchedule.MasterAgreementScheduleBuilder prune() {
			clause = clause.stream().filter(b->b!=null).<MasterAgreementClause.MasterAgreementClauseBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getClause()!=null && getClause().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public MasterAgreementSchedule.MasterAgreementScheduleBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			MasterAgreementSchedule.MasterAgreementScheduleBuilder o = (MasterAgreementSchedule.MasterAgreementScheduleBuilder) other;
			
			merger.mergeRosetta(getClause(), o.getClause(), this::getOrCreateClause);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			MasterAgreementSchedule _that = getType().cast(o);
		
			if (!ListEquals.listEquals(clause, _that.getClause())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (clause != null ? clause.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "MasterAgreementScheduleBuilder {" +
				"clause=" + this.clause +
			'}';
		}
	}
}
