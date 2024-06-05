package cdm.regulation;

import cdm.regulation.AcctOwnr;
import cdm.regulation.AcctOwnr.AcctOwnrBuilder;
import cdm.regulation.AcctOwnr.AcctOwnrBuilderImpl;
import cdm.regulation.AcctOwnr.AcctOwnrImpl;
import cdm.regulation.Id;
import cdm.regulation.meta.AcctOwnrMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * @version ${project.version}
 */
@RosettaDataType(value="AcctOwnr", builder=AcctOwnr.AcctOwnrBuilderImpl.class, version="${project.version}")
public interface AcctOwnr extends RosettaModelObject {

	AcctOwnrMeta metaData = new AcctOwnrMeta();

	/*********************** Getter Methods  ***********************/
	Id getId();

	/*********************** Build Methods  ***********************/
	AcctOwnr build();
	
	AcctOwnr.AcctOwnrBuilder toBuilder();
	
	static AcctOwnr.AcctOwnrBuilder builder() {
		return new AcctOwnr.AcctOwnrBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends AcctOwnr> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends AcctOwnr> getType() {
		return AcctOwnr.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("id"), processor, Id.class, getId());
	}
	

	/*********************** Builder Interface  ***********************/
	interface AcctOwnrBuilder extends AcctOwnr, RosettaModelObjectBuilder {
		Id.IdBuilder getOrCreateId();
		Id.IdBuilder getId();
		AcctOwnr.AcctOwnrBuilder setId(Id id);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("id"), processor, Id.IdBuilder.class, getId());
		}
		

		AcctOwnr.AcctOwnrBuilder prune();
	}

	/*********************** Immutable Implementation of AcctOwnr  ***********************/
	class AcctOwnrImpl implements AcctOwnr {
		private final Id id;
		
		protected AcctOwnrImpl(AcctOwnr.AcctOwnrBuilder builder) {
			this.id = ofNullable(builder.getId()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("id")
		public Id getId() {
			return id;
		}
		
		@Override
		public AcctOwnr build() {
			return this;
		}
		
		@Override
		public AcctOwnr.AcctOwnrBuilder toBuilder() {
			AcctOwnr.AcctOwnrBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(AcctOwnr.AcctOwnrBuilder builder) {
			ofNullable(getId()).ifPresent(builder::setId);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			AcctOwnr _that = getType().cast(o);
		
			if (!Objects.equals(id, _that.getId())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (id != null ? id.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "AcctOwnr {" +
				"id=" + this.id +
			'}';
		}
	}

	/*********************** Builder Implementation of AcctOwnr  ***********************/
	class AcctOwnrBuilderImpl implements AcctOwnr.AcctOwnrBuilder {
	
		protected Id.IdBuilder id;
	
		public AcctOwnrBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("id")
		public Id.IdBuilder getId() {
			return id;
		}
		
		@Override
		public Id.IdBuilder getOrCreateId() {
			Id.IdBuilder result;
			if (id!=null) {
				result = id;
			}
			else {
				result = id = Id.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("id")
		public AcctOwnr.AcctOwnrBuilder setId(Id id) {
			this.id = id==null?null:id.toBuilder();
			return this;
		}
		
		@Override
		public AcctOwnr build() {
			return new AcctOwnr.AcctOwnrImpl(this);
		}
		
		@Override
		public AcctOwnr.AcctOwnrBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public AcctOwnr.AcctOwnrBuilder prune() {
			if (id!=null && !id.prune().hasData()) id = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getId()!=null && getId().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public AcctOwnr.AcctOwnrBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			AcctOwnr.AcctOwnrBuilder o = (AcctOwnr.AcctOwnrBuilder) other;
			
			merger.mergeRosetta(getId(), o.getId(), this::setId);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			AcctOwnr _that = getType().cast(o);
		
			if (!Objects.equals(id, _that.getId())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (id != null ? id.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "AcctOwnrBuilder {" +
				"id=" + this.id +
			'}';
		}
	}
}
